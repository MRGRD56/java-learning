import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.*;
import java.util.List;

public class Program61 {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        Person person = new Person("First", 99, 900, List.of(
                new Person("Child1", 1, 901, List.of()),
                new Person("Child2", 2, 902, List.of())
        ));

        System.out.println("WRITE " + person);

        objectOutputStream.writeObject(person);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        System.out.println("READ " + objectInputStream.readObject());
    }

    private static class Person implements Externalizable {
        private String name;
        private int age;
        private int someCountIdk;
        private List<Person> children;

        public Person() {
        }

        public Person(String name, int age, int someCountIdk, List<Person> children) {
            this.name = name;
            this.age = age;
            this.someCountIdk = someCountIdk;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSomeCountIdk() {
            return someCountIdk;
        }

        public void setSomeCountIdk(int someCountIdk) {
            this.someCountIdk = someCountIdk;
        }

        public List<Person> getChildren() {
            return children;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(name);
            out.writeInt(age);
            out.writeInt(someCountIdk);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.name = (String) in.readObject();
            this.someCountIdk = in.readInt(); // incorrect order, I know
            this.age = in.readInt();
            this.children = (List<Person>) in.readObject();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("name", name)
                    .append("age", age)
                    .append("someCountIdk", someCountIdk)
                    .append("child", children)
                    .toString();
        }
    }
}
