package com.example.designmode.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoTest {

    /**
     * ByteArrayInputStream:
     * 1.构造函数中需要传入一个byte数组作为数据源，当执行read操作时，就会从该数组中读取数据;
     * 2.如果在构造函数中传入了null作为字节数据，那么在执行read操作时就会出现NullPointerException异常，
     * 但是在构造函数初始化阶段不会抛出异常
     * <p>
     * ByteArrayOutputStream:
     * 1.其内部也有一个字节数组用于存储write操作时写入的数据，在构造函数中可以传入一个size指定
     * 其内部的byte数组的大小，如果不指定，那么默认它会将byte数组初始化为32字节，
     * 当持续通过write向ByteArrayOutputStream中写入数据时，如果其内部的byte数组的剩余空间
     * 不能够存储需要写入的数据，那么那么它会通过调用内部的ensureCapacity()方法对其内部
     * 维护的byte数组进行扩容以存储所有要写入的数据，所以不必担心其内部的byte数组太小
     * 导致的IndexOutOfBoundsException之类的异常。
     */
    @Test
    public void testByteArrayInputOutputStream() {

        String str = "I am String!";
        byte[] bytes = str.getBytes();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len = 0;
        try {
            while ((len = bais.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
            System.out.println(baos.toString("UTF-8"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * FileInputStream 能够将文件作为数据源，读取文件中的流，通过File对象或文件路径等初始化
     *
     * @throws IOException
     */
    @Test
    public void testFileInputOutputStream() throws IOException {
        String inputFileName = "E:/notes/java/javaBasic/java-Enum.md";
        String outputFileName = "E:/temp/file.md";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(inputFileName);
            fos = new FileOutputStream(outputFileName);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = fis.read(buf)) > 0) {
                fos.write(buf, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
    }

    /**
     * PipedInputStream和PipedOutputStream一般是结合使用的，这两个类用于在两个线程间进行管道通信，
     * 一般在一个线程中执行PipedOutputStream 的write操作，而在另一个线程中执行PipedInputStream的read操作。
     * <p>
     * 可以在构造函数中传入相关的流将PipedInputStream 和PipedOutputStream 绑定起来，
     * 也可以通过二者的connect方法将二者绑定起来，一旦二者进进行了绑定，那么PipedInputStream
     * 的read方法就会自动读取PipedOutputStream写入的数据。
     * <p>
     * PipedInputStream的read操作是阻塞式的，当执行PipedOutputStream的write操作时，PipedInputStream会在
     * 另一个线程中自动读取PipedOutputStream写入的内容，如果PipedOutputStream一直没有执行write操作写入
     * 数据，那么PipedInputStream的read方法会一直阻塞PipedInputStream的read方法所运行的线程直至读到数据。
     * 单独使用PipedInputStream或单独使用PipedOutputStream时没有任何意义的，必须将二者通过connect方法（或在
     * 构造函数中传入对应的流）进行连接绑定，如果单独使用其中的某一个类，就会触发IOException: Pipe Not Connected.
     */
    @Test
    public void testPipeInputOutputStream() {

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();

        Thread writerThread = new Thread(() -> {
            String msg = "hello world.";
            System.out.println("writer thread send msg");
            try {
                pos.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readerThread = new Thread(() -> {
            byte[] buf = new byte[1024];

            System.out.println("reader thread revceive msg");
            try {
                int len = pis.read(buf);
                String message = new String(buf, 0, len, StandardCharsets.UTF_8);
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // 通过pos.connect(pis)将这两种流绑定在一起
        try {
            pos.connect(pis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readerThread.start();
        writerThread.start();
    }

    /**
     * 此处Person应该声明为static且实现Serializable接口
     * 如果为非static类型，则下述示例会报错 java.io.NotSerializableException
     * <p>
     * 原因：
     * 非静态内部类拥有对外部类的所有成员的完全访问权限，包括实例字段和方法。为实现这一行为，
     * 非静态内部类存储着对外部类的实例的一个隐式引用。序列化时要求所有的成员变量是Serializable,
     * 现在外部的类并没有implements Serializable,所以就抛出java.io.NotSerializableException异常。
     */
    static class Person implements Serializable {

        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    /**
     * ObjectOutputStream:
     * 具有一系列writeXXX方法，在其构造函数中可以掺入一个OutputStream，可以方便的向指定的输出流中
     * 写入基本类型数据以及String，比如writeBoolean、writeChar、writeInt、writeLong、writeFloat、
     * writeDouble、writeCharts、writeUTF等，除此之外，ObjectOutputStream还具有writeObject方法。
     * writeObject方法中传入的类型必须实现了Serializable接口，从而在执行writeObject操作时将对象
     * 进行序列化成流，并将其写入指定的输出流中。
     * ObjectInputStream:
     * 与ObjectOutputStream相对应，ObjectInputStream有与OutputStream中的writeXXX系列方法完全对应
     * 的readXXX系列方法，专门用于读取OutputStream通过writeXXX写入的数据。
     */
    @Test
    public void testObjectInputOutputStream() {
        try {
            String fileName = "E:/temp/file.txt";

            //将内存中的对象序列化到物理文件中
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            String description = "以下是人员数组";

            Person[] persons = new Person[]{
                    new Person("iSpring", 26),
                    new Person("Mr.Sun", 27),
                    new Person("Miss.Zhou", 27)
            };

            oos.writeObject(description);

            oos.writeInt(persons.length);

            for (Person person : persons) {
                oos.writeObject(person);
            }
            oos.close();

            //从物理文件中反序列化读取对象信息
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String str = (String) ois.readObject();
            System.out.println(str);
            int personCount = ois.readInt();
            for (int i = 0; i < personCount; i++) {
                Person person = (Person) ois.readObject();
                StringBuilder sb = new StringBuilder();
                sb.append("姓名: ").append(person.getName()).append(", 年龄: ").append(person.getAge());
                System.out.println(sb);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * SequenceInputStream:
     * 主要是将两个（或多个）InputStream在逻辑上合并为一个InputStream，
     * 比如在构造函数中传入两个InputStream，分别为in1和in2,那么SequenceInputStream在
     * 读取操作时会先读取in1，如果in1读取完毕，就会接着读取in2。
     * <p>
     * 在我们理解了SequenceInputStream 的作用是将两个输入流合并为一个输入流之后，
     * 我们就能理解为什么不存在对应的SequenceOutputStream 类了，因为将一个输出流拆分为多个输出流是没有意义的。
     */
    @Test
    public void testSequenceInputOutputStream() {
        String inputFileName1 = "E:/temp/file.txt";
        String inputFileName2 = "E:/temp/file1.txt";
        String outputFileName = "E:/temp/file2.txt";

        try {
            FileInputStream fis1 = new FileInputStream(inputFileName1);
            FileInputStream fis2 = new FileInputStream(inputFileName2);
            SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
            FileOutputStream fos = new FileOutputStream(outputFileName);

            byte[] buf = new byte[1024];
            int length;
            while ((length = sis.read(buf)) > 0) {
                fos.write(buf, 0, length);
            }

            sis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DataInputStream可以让你从InputStream读取Java基本类型来代替原始的字节。
     * DataInputStream来包装InputStream，你就可以从DataInputStream直接以Java基本类型来读取数据。
     * <p>
     * 如果你需要读取的数据是由大于一个字节的java基础类型构成，比如int, long, float, double等，
     * 那么用DataInputStream是很方便的。DataInputStream希望的数据是写入到网络的有序多字节数据。
     */
    @Test
    public void testDataInputStream() {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("E:/temp/file.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.writeInt(123);
            dataOutputStream.writeFloat(123.45F);
            dataOutputStream.writeLong(789);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream("E:/temp/file.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int int123 = 0;
        float float12345 = 0;
        long long789 = 0;
        try {
            int123 = dataInputStream.readInt();
            float12345 = dataInputStream.readFloat();
            long789 = dataInputStream.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("int123     = " + int123);
        System.out.println("float12345 = " + float12345);
        System.out.println("long789    = " + long789);
    }

    /**
     * 打印流 -> PrintWriter
     */
    @Test
    public void testPrintStream() {
        PrintStream ps = null;        // 声明打印流对象
        // 使用FileOuputStream实例化，意味着所有的输出是向文件之中
        try {
            String inputFileName = "E:/temp/file.txt";
            ps = new PrintStream(new FileOutputStream(inputFileName));
            ps.print("hello ");
            ps.println("world!!!");
            ps.print("1 + 1 = " + 2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * 回退流：
     * 在JAVA IO中所有的数据都是采用顺序的读取方式，即对于一个输入流来讲都是采用从头到尾的顺序读取的，
     * 如果在输入流中某个不需要的内容被读取进来，则只能通过程序将这些不需要的内容处理掉，为了解决这样
     * 的处理问题，在JAVA中提供了一种回退输入流（PushbackInputStream、PushbackReader），可以把读取进
     * 来的某些数据重新回退到输入流的缓冲区之中。
     * <p>
     * 常用方法
     * 1、public PushbackInputStream(InputStream in) 构造方法 将输入流放入到回退流之中。
     * 2、public int read() throws IOException   普通读取数据。
     * 3、public int read(byte[] b,int off,int len) throws IOException 普通方法 读取指定范围的数据。
     * 4、public void unread(int b) throws IOException 普通方法 回退一个数据到缓冲区前面。
     * 5、public void unread(byte[] b) throws IOException 普通方法 回退一组数据到缓冲区前面。
     * 6、public void unread(byte[] b,int off,int len) throws IOException 普通方法 回退指定范围的一组数据到缓冲区前面。
     */
    @Test
    public void testPushBackInputStream() {
        String str = "www.baidu.com";        // 定义字符串

        PushbackInputStream push = null;        // 定义回退流对象
        ByteArrayInputStream bai = null;        // 定义内存输入流

        bai = new ByteArrayInputStream(str.getBytes());    // 实例化内存输入流
        push = new PushbackInputStream(bai);    // 从内存中读取数据

        System.out.print("读取之后的数据为：");

        int temp = 0;
        while (true) {
            try {
                if ((temp = push.read()) == -1) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }    // 读取内容
            if (temp == '.') {    // 判断是否读取到了“.”
                try {
                    push.unread(temp);    // 放回到缓冲区之中
                    temp = push.read();    // 再读一遍
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print("（退回" + (char) temp + "）");
            } else {
                System.out.print((char) temp);    // 输出内容
            }
        }
    }

    /**
     * BufferedInput
     **/
    @Test
    public void testBufferedInputOutputStream() {
        try {
            String inputFileName = "E:/temp/file.txt";
            String outputFileName = "E:/temp/file1.txt";

            FileInputStream fis = new FileInputStream(inputFileName);
            BufferedInputStream bis = new BufferedInputStream(fis, 1024 * 10);

            FileOutputStream fos = new FileOutputStream(outputFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 1024 * 10);

            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = bis.read(buf)) > 0) {
                bos.write(buf, 0, length);
            }

            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节数据转换为字符数据
     **/
    @Test
    public void testInputStreamReader() throws IOException {
        // 其中InputStream类是被适配者，InputStreamReader 是她的适配器。通过适配器模式我们就可以通过字节流对象去读取字符了。
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/temp/ArraySumTask.class"))));
        // BufferedWriter writer = new BufferedWriter(reader);
        char[] buf = new char[1024 * 5];
        int length = 0;
        while((length = reader.read(buf)) > 0){
            System.out.println();
        }

    }

    /**
     * 字符数据转换为字节数据
     **/
    @Test
    public void testOutputStreamWriter() throws FileNotFoundException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File("D:/temp/123.txt")));
    }
}
