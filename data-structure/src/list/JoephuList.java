package list;

/**
 * 单向环形列表 又叫约瑟夫列表
 */
public class JoephuList {

    private class Boy {
        private int no; //编号
        private Boy next;//指向下一个节点，默认为null

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }


    //创建一个first节点，当前咩有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的列表
    public void addBoy(int nums) {
        //nums做一个校验
        if (nums < 1) {
            System.out.println("nums的值不正确。");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy currBoy = null;
        //用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                //如果是第一个小孩
                first = boy;
                first.setNext(first);
                currBoy = first;
            } else {
                boy.setNext(currBoy.getNext());
                currBoy.setNext(boy);
                currBoy = boy;
            }

        }

    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩");
        } else {
            //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
            Boy curBoy = first;
            while (true) {
                System.out.println(String.format("小孩的编号：%d", curBoy.getNo()));
                if (curBoy.getNext() == first) {
                    break;
                }
                curBoy = curBoy.getNext();
            }

        }
    }


    /**
     * 根据自己的理解写的方法
     * 根据用户的输入，计算出出圈的顺序
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初又多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if(first == null ){
            return;
        }
        if(startNo < 1 || countNum <= 1  || startNo > nums){
            System.out.println("输入数据错误！");
            return;
        }
        //先移动小孩到开始计数的位置，然后定义一个零时变量，用来做标记
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
        }

        Boy curBoy = first;
        Boy tempBoy = null;
        while (curBoy.getNext() != first){
            curBoy = curBoy.getNext();
            tempBoy = curBoy;
        }

        while (tempBoy != first){
            for (int i = 1; i <= countNum; i++) {
                if(i == countNum){
                    //小孩出圈
                    System.out.println("出圈的小孩：" + first.getNo());
                    first = first.getNext();
                    tempBoy.setNext(first);
                }else{
                    //指针移动
                    tempBoy = first;
                    first = first.getNext();
                }
            }
        }
        System.out.println("最后剩余的小孩：" +first.getNo());

    }

    public void countBoy2(int startNo,int countNum,int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入！");
            return;
        }
        //创建要给辅助指针，帮助小孩出圈
        Boy helper = first;
        //需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后一个节点
        while (true){
            if(helper.getNext() == first){//说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，想让helper和first移动k-1次
        for (int j = 0; j <  startNo -1 ; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if(helper == first){//说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countNum-1
            for (int j = 0; j < countNum -1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo() );
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());

    }

    public static void main(String[] args) {
        JoephuList joephuList  = new JoephuList();
        joephuList.addBoy(5);
        joephuList.showBoy();
//        joephuList.countBoy(1,2,5);
        joephuList.countBoy2(1,2,5);
    }
}
