/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author khyzar
 */
public class Lex {

    
    static int index_of_code=0;
    static ArrayList buffer=new ArrayList();
    static String ss="";
    
    /**
     * @param args the command line arguments
     */
//   static boolean isletter(char i)
//    {
//        if(i>='a' && i<='z' || i>='A' && i<='Z')
//            return true;
//        return false;
//        
//        
//    }
     static void print_token_lexeme(String pair)
    {
        try { 
			File file = new File("C:\\Users\\khyzar\\Documents\\NetBeansProjects\\lex\\src\\lex\\tokens.txt");

			// if file doesnt exists, then create itb
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pair);
			bw.close();

			System.out.println("Done");
                       

		} catch (IOException e) {
			e.printStackTrace();
		}        
    }
     
   static boolean  check_ID(char[] array)
    {
        int state=1;
      //  System.out.println(array);
        String ID="";
        String content="";
        int temp=index_of_code;
       // System.out.println(temp);
        
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(Character.isLetter(array[i]))
                    {
                        state=2;
                        ID=ID+array[i];
                        temp++;
                        break;
                    }
                    else {
                        if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                        
                case 2:
                    if(Character.isLetter(array[i]))
                    {
                        state=2;
                        ID=ID+array[i];
                        temp++;
                       break;
                    }
                    else 
                    {    state=3;
                    
                    break;
                    }
                
                case 3:
                    index_of_code=temp;
                    if(buffer.contains(ID))
                    {
                     content="("+ID+",null),";   
                    }
                    else
                    {
                        content = "(ID,"+ID+"),";
                         try {

			 
                             String scontent=ID;
			File file2 = new File("C:\\Users\\khyzar\\Documents\\NetBeansProjects\\lex\\src\\lex\\Stable.txt");

			// if file doesnt exists, then create itb
			if (!file2.exists()) {
				file2.createNewFile();
			}

			FileWriter fww = new FileWriter(file2.getAbsoluteFile(),true);
			BufferedWriter bww = new BufferedWriter(fww);
                        PrintWriter pw = new PrintWriter(bww);
			//bww.write(scontent);
			//bww.close();
                        pw.println(ID);
                        pw.close();
			//System.out.println("Done");
                       //return true;

		} catch (IOException e) {
			e.printStackTrace();
		}
                    }                    
//System.out.println(index_of_code);
                   print_token_lexeme(content);
                    return true;
                    
                
                
                
            }
        }
        index_of_code=temp;
        return true;
    }
    static boolean check_Num(char[] array)
    {
        
         int state=1;
      //  System.out.println(array);
        String Num="";
        String content="";
        int temp=index_of_code;
       // System.out.println(temp);
        
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(Character.isDigit(array[i]))
                    {
                        state=2;
                        Num=Num+array[i];
                        temp++;
                        break;
                    }
                    else 
                    {
                         if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                case 2:
                    if(Character.isDigit(array[i]))
                    {
                        state=2;
                        Num=Num+array[i];
                        temp++;
                       break;
                    }
                    else 
                    {    state=3;
                    
                    break;
                    }
                
                case 3:
                    index_of_code=temp;
                    
                    content = "(Num,"+Num+"),";                       
//System.out.println(index_of_code);
                   print_token_lexeme(content);
                    return true;
            }
        }
        
        
        index_of_code=temp;
        return true;
    }
    
    
    static boolean check_LO(char[] array)
    {
         int state=1;
      //  System.out.println(array);
        String LO="";
        String content="";
        int temp=index_of_code;
       // System.out.println(temp);
        
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(array[i]=='<')
                    {
                        state=2;
                        LO=LO+array[i];
                        temp++;
                        break;
                    }
                    else if(array[i]=='>')
                    {
                        state=5;
                        LO=LO+array[i];
                        temp++;
                        break;
                    }
                    else if(array[i]=='=')
                    {
                        state=8;
                        LO=LO+array[i];
                        temp++;
                        break;   
                    }
                    else if(array[i]=='!')
                    {
                        state=11;
                        LO=LO+array[i];
                        temp++;
                        break;   
                    }
                    else 
                    {
                     
                         if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                case 2:
                    if(array[i]=='=')
                    {
                        state=4;
                        temp++;
                       break;
                    }
                    else 
                    {    state=3;
                    
                    break;
                    }
                case 3:
                    ////// yaha se start kerna hai ek global buffer bana ke
                    ss="(LT,"+LO+"),";
                    index_of_code=temp;
                    return true;
                case 4:
                    ss="(LE,"+LO+"),";
                    index_of_code=temp;
                    return true;
                case 5:
                     if(array[i]=='=')
                    {
                        state=7;
                        temp++;
                       break;
                    }
                    else 
                    {    state=6;
                    
                    break;
                    }
                case 6:
                     ss="(GT,"+LO+"),";
                     index_of_code=temp;
                    return true;
                case 7:
                     ss="(GE,"+LO+"),";
                     index_of_code=temp;
                    return true;
                case 8:
                     if(array[i]=='=')
                    {
                        state=10;
                        temp++;
                       break;
                    }
                    else 
                    {    state=9;
                    
                    break;
                    }
                case 9:
                    ss="(Eq,"+LO+"),";
                    index_of_code=temp;
                    return true;
                case 10:
                    ss="(DEq,"+LO+"),";
                    index_of_code=temp;
                    return true;
                case 11:
                     if(array[i]=='=')
                    {
                        state=12;
                        temp++;
                       break;
                    }
                case 12:
                     ss="(NEq,"+LO+"),";
                     index_of_code=temp;
                    return true;
            
            }
        }
        
        return true;
    }
    
    
    static boolean check_brac(char[] array)
    {
        int state=1;
         String brac="";
        String content="";
        int temp=index_of_code;
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(array[i]=='{')
                    {
                        state=2;
                        brac=brac+array[i];
                        temp++;
                        break;
                    }
                    else if(array[i]=='}')
                    {
                       state=4;
                       brac=brac+array[i];
                       temp++;
                       break;
                    }
                     else if(array[i]=='[')
                    {
                       state=6;
                       brac=brac+array[i];
                       temp++;
                       break;
                    }
                    else if(array[i]==']')
                    {
                       state=8;
                       brac=brac+array[i];
                       temp++;
                       break;
                    }
                    else if(array[i]=='(')
                    {
                       state=10;
                       brac=brac+array[i];
                       temp++;
                       break;
                    }
                    else if(array[i]==')')
                    {
                       state=12;
                       brac=brac+array[i];
                       temp++;
                       break;
                    }
                    else 
                    {
                         if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                case 2:
                    state=3;
                   
                    break;
                case 3:
                    ss="(LKB,"+brac+"),";
                    index_of_code=temp;
                    return true;
                case 4:
                    state=5;
                   
                    break;
                case 5:
                    ss="(RKB,"+brac+"),";
                    index_of_code=temp;
                    return true;
                case 6:
                    state=7;
                    
                    break;
                case 7:
                    ss="(LSB,"+brac+"),";
                    index_of_code=temp;
                    return true;
                case 8:
                    state=9;
                   
                    break;
                case 9:
                    ss="(RSB,"+brac+"),";
                    index_of_code=temp;
                    return true;
                case 10:
                    state=11;
                    
                    break;
                case 11:
                    ss="(LP,"+brac+"),";
                    index_of_code=temp;
                    return true;
                case 12:
                    state=13;
                   
                    break;
                case 13:
                    ss="(RP,"+brac+"),";
                    index_of_code=temp;
                    return true;
                        
            }
        }
         
        return true;
    }
    
    static boolean check_Arth(char[] array)
    {
        int state=1;
         String Arth="";
        String content="";
        int temp=index_of_code;
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(array[i]=='+')
                    {
                        state=2;
                        Arth=Arth+array[i];
                        temp++;
                        break;
                    }
                    else if(array[i]=='-')
                    {
                       state=4;
                       Arth=Arth+array[i];
                       temp++;
                       break;
                    }
                     else if(array[i]=='*')
                    {
                       state=6;
                       Arth=Arth+array[i];
                       temp++;
                       break;
                    }
                    else if(array[i]=='/')
                    {
                       state=8;
                       Arth=Arth+array[i];
                       temp++;
                       break;
                    }
                    else 
                    {
                         if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                case 2:
                    state=3;
                   
                    break;
                case 3:
                    ss="(Add,"+Arth+"),";
                    index_of_code=temp;
                    return true;
                case 4:
                    state=5;
                    
                    break;
                case 5:
                    ss="(Sub,"+Arth+"),";
                    index_of_code=temp;
                    return true;
                case 6:
                    state=7;
                   
                    break;
                case 7:
                    ss="(Mul,"+Arth+"),";
                    index_of_code=temp;
                    return true;
                case 8:
                    state=9;
                    
                    break;
                case 9:
                    ss="(Div,"+Arth+"),";
                    index_of_code=temp;
                    return true;
                
                        
            }
        }
        
        return true;
    }
   
    
    static boolean check_other(char[] array)
    {
         int state=1;
         String other="";
        String content="";
        int temp=index_of_code;
        for(int i=temp; index_of_code<array.length; i++)
        {
            switch(state)
            {
                case 1:
                    if(array[i]==';')
                    {
                        state=2;
                        other=other+array[i];
                        temp++;
                        break;
                    }
                    else if(array[i]==',')
                    {
                       state=4;
                       other=other+array[i];
                       temp++;
                       break;
                    }
                    else 
                    {
                         if(array[i]==' ')
                    {
                        temp++;
                        index_of_code=temp;
                    }
                        return false;
                    }
                case 2:
                    state=3;
                   
                    break;
                case 3:
                    ss="("+other+",null),";
                    index_of_code=temp;
                    return true;
                case 4:
                    state=5;
                   
                    break;
                case 5:
                    ss="("+other+",null),";
                    index_of_code=temp;
                    return true;        
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
       buffer.add("if");
       buffer.add("else");
       buffer.add("void");
       buffer.add("return");
       buffer.add("while");
       buffer.add("int");
       
       
        String filename="C:\\Users\\khyzar\\Documents\\NetBeansProjects\\lex\\src\\lex\\input.txt";
//           for (String s: args) {
//            System.out.println(s);
//            filename=filename+s;
//        }
//           System.out.println(filename);
           String theString = "";

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        char[] code=null;
        theString = scanner.nextLine();
       
        while (scanner.hasNext()) {
               theString = theString  + scanner.next();

         //charArray = theString.toCharArray();

         }
        code = theString.toCharArray();
        //for (int i=0; i <charArray.length; i++);
       // System.out.println(charArray[7]);
        for(int i=0; index_of_code<code.length; i++)
        {
            if(check_ID(code))
            {
                continue;
            }
            if(check_Num(code))
            {
                continue;
            }
            if(check_LO(code))
            {
                print_token_lexeme(ss);
                continue;
            }
            if(check_Arth(code))
            {
                print_token_lexeme(ss);
                continue;
            }
            if(check_brac(code))
            {
                print_token_lexeme(ss);
                continue;
            }
            if(check_other(code))
            {
                print_token_lexeme(ss);
                continue;
            }
        }
        
    }
    
}
