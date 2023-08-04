import dev.b37.mgutils.concurrent.TaskInvoker;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.*;
import java.net.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Program59 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private static final Lock serverCreatedLock = new ReentrantLock();
    private static final Condition serverCreatedCondition = serverCreatedLock.newCondition();

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        taskInvoker.submit(() -> {
            serverCreatedLock.lock();
            try {
                serverCreatedCondition.await();

                try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), 29105);
                     OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
                     GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {

                    Person person = new Person();
                    person.setFirstName("John");
                    person.setLastName("Johnson");
                    person.setGender(Gender.MALE);
                    person.setBirthDate(LocalDate.of(2000, Month.APRIL, 9));

                    objectOutputStream.writeObject(person);

                    System.out.println("Sent the person: " + person);
                }
            } finally {
                serverCreatedLock.unlock();
            }
        });

        taskInvoker.submit(() -> {
            try (ServerSocket serverSocket = new ServerSocket(29105)) {
                serverCreatedLock.lock();
                try {
                    serverCreatedCondition.signal();
                } finally {
                    serverCreatedLock.unlock();
                }

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
                }
            }
        });

        taskInvoker.completeAllVoid();
        executor.shutdown();
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
