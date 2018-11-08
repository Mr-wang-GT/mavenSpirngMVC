package cn.wgt.controller;

public class test {
    public static void main(String args[]){
        String hobby="  b   b";
        String[] temp= hobby.replace('#',' ').split("\\s+");
        hobby="";
        for (String str:temp
             ) {if(str.equals("")){
                    continue;
                }
                 else{
                     hobby+="#"+str+" ";
                }
        }
        System.out.println(hobby);
    }
}
