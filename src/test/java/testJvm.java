public class testJvm {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机视图使用的最大内存
        long totalMemory = Runtime.getRuntime().totalMemory();//当前虚拟机内存总量
        System.out.println("maxMemory=" + maxMemory + "字节" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("totalMemory=" + totalMemory + "字节" + (totalMemory / (double) 1024 / 1024) + "MB");
    }

    public void test() {
        //新建一个长度为4数组
        int[] arr = new int[]{1, 2, 3, 4};
        //访问数组的第一个元素
        int i = arr[0];
        //修改数组的第二个元素
        arr[1] = i;

        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        }
    }
}
//-Xmx1024m -Xms1024m -XX:+PrintGCDetails
