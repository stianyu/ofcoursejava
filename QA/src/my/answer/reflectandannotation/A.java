package my.answer.reflectandannotation;

public class A {

    @Before
    public void init() {
        System.out.println("初始化方法");
    }

    public void test1() {
        System.out.println("这个方法不会被测试");
    }

    @Test
    public void test2() {
        System.out.println("测试方法2执行了");
    }


    @Test
    public void test3() {
        System.out.println("测试方法3执行了");
    }

    @Test
    public void test4() {
        int a = 1 / 0;
        System.out.println("测试方法4执行了");
    }

    @After
    public void finish() {
        System.out.println("结束方法");
    }
}