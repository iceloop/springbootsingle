package com.example.springboot;

public class sortingtest {
    public static void main(String[] args) {

    }
    //选择排序
    public static int[] orderBySelect(int[] a){
        for(int i=0;i<a.length;i++){
            int temp=a[i];
            int flag=i;
            for(int j=i+1;j<a.length;j++){
                if(temp>a[j]){
                    temp=a[j];
                    flag=j;
                }
            }
            if(flag!=i){
                a[flag]=a[i];
                a[i]=temp;
            }
        }
        return a;
    }
//插入排序
    public static int[] orderByInsert(int[] a){
        for(int i=1;i<a.length;i++){
            int temp=a[i];//保存当前将要用于插入的值
            int j=i-1;//用于遍历已经排好序的子集的下标
            if(temp<a[j]){//判断子集的最大值与当前的值的大小，如果当前值大，则不需要循环
                while(j>=0 && a[j]>temp){//如果子集的元素大于当前值，则修改当前值的位置
                    a[j+1]=a[j];//将j的位置的值向前移动,用于存放当前值
                    j--;//进入下一次循环
                }
                a[j+1]=temp;//循环结束后子集中所有大于temp的值都向前移动了一步，这时候j+1的位置就是temp应该插入的位置
            }
        }
        return a;
    }
    //冒泡排序
    public static int[] orderByBubble(int[] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        return a;
    }
    /**
     * 归并排序(递归和合并)
     * @param a 目标数组
     * @param p 目标数组的起始位置
     * @param r 目标数组的结束位置
     */
    public static void mergeSort(int[] a, int p, int r) {
        if(p<r){//条件判断，直到将数组细分到为一个元素作为一个数组为止
            int q = (p+r)/2;//平分数组向下取整
            mergeSort(a, p, q);//对数组左半部分递归细分
            mergeSort(a, q+1, r);//对数组右半部分递归细分
            merge(a,p,q,r);//由条件可以看出，这个方法将在数组被细分到元素个数为2的时候被调用(当元素为1即r=0时，什么也不执行)
        }
    }

    /**
     * 对细分数组已经经过排序后的左半部分和右半部分进行合并排序
     * 当细分数组的元素只有一个时，则仅仅是比较两个单元素数组的大小进行排序
     * @param a 目标数组
     * @param p 起始位置
     * @param q 终止位置
     * @param r 数组大小
     */
    private static void merge(int[] a, int p, int q, int r) {
        int i,j,k,n1,n2;
        n1=q-p+1;//定义左半部分数组的大小
        n2=r-q;//定义右半部分数组的大小
        int[] L=new int[n1];//初始化左半部分数组
        int[] R=new int[n2];//初始化右半部分数组
        for(i=0,k=p;i<n1;i++,k++)//赋值
            L[i]=a[k];
        for(j=0,k=q+1;j<n2;j++,k++)//赋值
            R[j]=a[k];
        for(i=0,j=0,k=p;i<n1&&j<n2;k++){//归并左半部分和右半部分，可以看到循环的终止条件是左或者右数组的元素全部放到了目标数组中
            if(L[i]<R[j]){//如果左半部分第一个元素大于右半部分第一个元素，则将目标数组开始位置的值设置为L[0]并递归
                a[k]=L[i];
                i++;
            }else{
                a[k]=R[j];//否则将目标数组开始位置的值设置为R[0]并递归
                j++;
            }
        }
        if(i<n1){//执行这个条件表明L数组中存在大于R中最大元素的元素，这时候循环将这些元素放到目标中
            for(j=i;j<n1;j++,k++)
                a[k]=L[j];
        }
        if(j<n2){//执行这个条件表明R数组中存在大于L中最大元素的元素，这时候循环将这些元素放到目标中
            for(i=j;i<n2;i++,k++)
                a[k]=R[i];
        }
    }

    public static void quickSort(int[] a){
        sort(a,0,a.length-1);
    }

    /**
     * 快速排序
     * @param a 目标数组
     * @param begin 起始下标
     * @param end 终止下标
     */
    private static void sort(int[] a, int begin, int end) {
        int i,j,index;
        if(begin>=end)//数组合法性判定
            return;
        i=begin;
        j=end;
        index=a[i];//拷贝第一个元素
        //以下循环主要完成将一个数组平分成两部分，左边部分的每一个值均小于右边的每一个值
        //原理：一次循环中分别从尾端向首端遍历找到一个小于index的元素并赋值给a[i](a[i]的值保存在index中不会丢失),这时候尾端的元素可
        //通过j作为下标找到，接下来通过首端向尾端遍历找到一个大于index的元素并赋值给之前已经赋值到左
        //部分的元素，这些条件都是在i<j的情况下执行的，保证了遍历不会过界的问题，
        while(i<j){
            while(i<j&&a[j]>=index)
                j--;
            if(i<j)
                a[i++]=a[j];
            while(i<j&&a[i]<index)
                i++;
            if(i<j)
                a[j--]=a[i];
        }
        a[i]=index;
        sort(a,begin,i-1);//递归直到只有一个元素的数组，这时候整个数组就已经排好序
        sort(a,i+1,end);
//dfgdgf
//        dfv
    }
}
