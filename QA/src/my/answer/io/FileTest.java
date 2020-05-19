package my.answer.io;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {
    //1.1)
    @Test
    public void test1_1() {
        File fa = new File("C:\\a\\A.txt");
        System.out.println(File.pathSeparator);
        String[] path = fa.getAbsolutePath().split("\\\\");
        System.out.println(Arrays.toString(path));
        StringBuilder pathB = new StringBuilder();
        for (int i = 0; i < path.length-1; i++) {
            pathB.append(path[i]);
            pathB.append("\\");
        }
        pathB.append("b.txt");
        File fb = new File(pathB.toString());
    }

    //1.2)
    @Test
    public void test1_2() {
        File fa = new File("C:\\a\\A.txt");
        File fb = new File(fa.getParentFile().getParent()+"/b/B.txt");
        File fb2 = new File(fa,"../../b/B.txt");
        System.out.println(fa.exists());
        System.out.println(fb.exists());
        System.out.println(fb2.exists());

    }

    //2
    @Test
    public void test2() {
        File fa = new File("C:\\a\\b\\file.txt");
        if (!fa.getParentFile().exists()) {
            fa.getParentFile().mkdirs();
        }
        try {
            fa.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3_1() {
        //3.1)
        File fa = new File("C:\\a\\b\\file.txt");
        File fb = new File(fa, "../newFile.txt");
        System.out.println(fa.renameTo(fb));

        //3.2)
        System.out.println(fb.renameTo(new File(fb, "../../" + fb.getName())));

        //3.3)
        File fb2 = new File(fa.getParent() + "\\newFile.txt");
        fa.renameTo(fb2);
        try {
            File fbNew = new File(fa.getParentFile().getParent() + "\\" + fb2.getName());
            fbNew.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fb2.delete();

        //3.4)

        File f = new File("F:\\B站黑马程序员");
        getAllFiles2(f);
    }


    public static void getAllFiles1(File f) {
        File[] fLists= f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()) {
                    return true;
                }
                return pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".png");
            }
        });
        for (File file: fLists ) {
            if (file.isDirectory()) {
                getAllFiles1(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }

    public static void getAllFiles2(File f) {
        File[] fLists= f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()) {
                    return true;
                }
                return pathname.length() > 2*1024;
            }
        });
        for (File file: fLists ) {
            if (file.isDirectory()) {
                getAllFiles2(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }
}
