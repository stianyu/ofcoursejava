package my.answer.io;

import java.io.*;

public class FileIOTest {
    public static void main(String[] args) throws IOException {
        //3.1
        try (FileInputStream fis = new FileInputStream("A.txt");
        FileOutputStream fos = new FileOutputStream("ACopy.txt");) {
            byte[] bBuf = new byte[10240];
            int len;
            while((len = fis.read(bBuf)) != -1) {
                fos.write(bBuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.2
        try (FileReader fr = new FileReader("A.txt");) {
            int b;
            char[] cBuf = new char[1024];
            while ((b = fr.read(cBuf)) != -1) {
                System.out.println(new String(cBuf, 0, b).getBytes("utf-8"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.3
        //使用String的API读取
        FileInputStream fis = new FileInputStream("IOStream\\IO流问题.txt");
        byte[] b = new byte[fis.available()];
        fis.read(b);
        System.out.println(new String(b, "utf-8"));
        fis.close();

        //使用转换流读取
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("IOStream\\我是GBK格式的文本.txt"), "GBK");) {
            int read;
            char[] cBuf = new char[1024];
            while ((read = isr.read(cBuf)) != -1) {
                System.out.println(new String(cBuf, 0, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.4
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream("IOStream\\我是GBK格式的文本.txt"), "GBK");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("IOStream\\我是GBK格式的文本复印件.txt"),"utf-8");) {
            int read;
            char[] cBuf = new char[1024];
            while ((read = isr.read(cBuf)) != -1) {
                osw.write(cBuf, 0, read);
                System.out.println(new String(cBuf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.6
        File f = new File("D:\\9 会议PPT&书籍\\壁纸\\壁纸.jpg");
        FileSplit.split(f, "D:\\9 会议PPT&书籍\\壁纸\\分割");
        System.out.println(f.length());
        FileSplit.merge("D:\\9 会议PPT&书籍\\壁纸\\分割");
    }

}

class FileSplit {
    public static void split(File file, String path) {
        byte[] bBuf = new byte[1024 * 100];
        try (FileInputStream fis = new FileInputStream(file);) {
            int length;
            for (int i = 0; (length = fis.read(bBuf)) != -1; i++) {
                File fSplit = new File(path + "\\" + file.getName() + "-" + i);
                try (FileOutputStream fos = new FileOutputStream(fSplit);) {
                    fos.write(bBuf, 0, length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File merge(String path) {
        File mergeFile = new File(path + "\\合并.jpg");
        File directory = new File(path);
        File[] files = directory.listFiles();
        try(FileOutputStream fos = new FileOutputStream(mergeFile);) {
            for (File f : files) {
                try(FileInputStream fis = new FileInputStream(f);) {
                    int length;
                    byte[] bBuf = new byte[1024];
                    while ((length = fis.read(bBuf)) != -1) {
                        fos.write(bBuf, 0, length);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mergeFile;
    }
}



