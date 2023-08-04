import dev.b37.mgutils.concurrent.TaskInvoker;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Program60 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private static final BlockingQueue<Integer> serverCreatedQueue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        taskInvoker.submit(() -> {
            Integer serverPort = serverCreatedQueue.take();

            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), serverPort)) {
                try (OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                     GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {
                    Person person = new Person();
                    person.setFirstName("John");
                    person.setLastName("Johnson");
                    person.setGender(Gender.MALE);
                    person.setBirthDate(LocalDate.of(2000, Month.APRIL, 9));

                    objectOutputStream.writeObject(person);
                    gzipOutputStream.finish();
                    outputStream.flush();

                    System.out.println("Sent the person: " + person);

                    try (InputStream inputStream = new BufferedInputStream(socket.getInputStream());
                         GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                         ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream)) {
                        System.out.println("Got response[0]: " + objectInputStream.readObject());
                        System.out.println("Got response[1]: " + objectInputStream.readObject());
                    }
                }
            }
        });

        taskInvoker.submit(() -> {
            try (ServerSocket serverSocket = new ServerSocket(29105)) {
                serverCreatedQueue.add(serverSocket.getLocalPort());

                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = new BufferedInputStream(socket.getInputStream());
                     GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                     ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream)) {

                    Object object = objectInputStream.readObject();
                    if (object instanceof Person person) {
                        System.out.println("Got the person: " + person);
                    } else {
                        System.err.println("Not a person: " + object);
                    }


                    try (OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                         GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
                         ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {

                        Reference<Object> reference = new Reference<>(object);
                        objectOutputStream.writeObject(reference);
                        objectOutputStream.writeObject("FINISH");
                    }
                }
            }
        });

        taskInvoker.completeAllVoid();
        executor.shutdown();
    }

    public static class Reference<T> implements Serializable {
        private T value;

        public Reference() {
        }

        public Reference(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("value", get())
                    .toString();
        }
    }

    private static class Person implements Serializable {
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDate birthDate;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("firstName", firstName)
                    .append("lastName", lastName)
                    .append("gender", gender)
                    .append("birthDate", birthDate)
                    .toString();
        }
    }

    private enum Gender {
        MALE, FEMALE
    }
}
