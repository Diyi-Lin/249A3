// -------------------------------------------------------
	// Assignment3
    // Written by: (Diyi Lin student id40086388)
	// For COMP 249 Section  – winter 2019
	// --------------------------------------------------------
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class AuthorBibCreator {
	
	public static String processBibFiles(File save,String author,String formats) {
		
		String out="",no=null,au=null,jou=null,title=null,year=null,vol=null,num=null,pages=null,month=null,doi=null;
		int a,b,i=0;
		try 
		{ Scanner sc = new Scanner(new FileInputStream(save));
		   while(sc.hasNextLine()) {
			 String line = sc.nextLine();
		    
			 if(line.contains(author))
		     { a = line.indexOf("{")+1; b = line.indexOf("}");
		       au = line.substring(a,b);
		       
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        jou = line.substring(a,b);
		      
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        title = line.substring(a,b);
		       
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        year = line.substring(a,b);
		       
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        vol =  line.substring(a, b);
		       
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        num =  line.substring(a, b);
		     
		       line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        pages =  line.substring(a, b);
		     
		       sc.nextLine();line=sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        doi =  line.substring(a, b);
		       
		       sc.nextLine();line = sc.nextLine();
		       a = line.indexOf("{")+1; b = line.indexOf("}");
		        month =  line.substring(a, b);
		       i++;
		       no = "["+i+"]";
		    
		      if(formats.equals("ieee"))
		        out = out+au.replaceAll(" and ",", ")+". \""+title+"\", "+jou+", "+"vol."+vol+", no."+num+", p. "+pages+", "+month+" "+year+". \n";
		      else if(formats.equals("acm"))
		    	out = out+no+"	"+au.substring(0,au.indexOf(" and"))+" et al. "+year+". "+title+". "+jou+". "+vol+"("+year+"),"+pages+". DOI:https://doi.org/"+doi+".\n";
		      else if(formats.equals("nj"))
		    	out = out+au.replaceAll("and", "&")+". "+title+". "+jou+". "+vol+", "+pages+"("+year+").\n";
		     }
		     }
		   sc.close(); 
		   return out;
		}
		catch(FileNotFoundException e)
		{   out = "Source not found!";
			return out;
		}   	 	
	}
	
	public static void main(String[] args) {

	String a=null,b,author;
	PrintWriter pw = null;
	Scanner key = new Scanner(System.in);
	Scanner sc = null;
	File file = null,saveFile = null;
	File[] list = null;
	
	
	System.out.println("Welcome to bibCreator!\n\nPleasse enter the author name you are targeting:");
	
    author = key.nextLine();
	String nameIeee = author + "-IEEE.json";
	String nameAcm = author + "-ACM.json";
	String nameNj = author + "-NJ.json";
	String nameIeeeBU = author + "-IEEE-BU.json";
	String nameAcmBU = author + "-ACM-BU.json";
	String nameNjBU = author + "-NJ-BU.json"; 
	
	try
	{
	  File folder = new File("G:\\workspace\\249Assignment3");
	  pw = new PrintWriter(new FileOutputStream("save"));
	  saveFile = new File("save");
	  if(saveFile.exists())
	  {  saveFile.delete();
	  pw = new PrintWriter(new FileOutputStream("save"));
	  }
	  for(int i = 1; i<= 10; i++)
	  { a = "Latex"+i+".bib";
	    b = "G:\\workspace\\249Assignment3\\Assg_3-Needed-Files\\"+a;	    
	    sc = new Scanner(new FileInputStream(b));   
	    while (sc.hasNextLine())
          pw.println(sc.nextLine());	
	    }

	  list = folder.listFiles();
	  for(File files : list)
	  {
		 if(files.getName().equals(nameIeee)|files.getName().equals(nameAcm)|files.getName().equals(nameNj) )
		 throw new FileExsitsException();
	  }
	  
	  a = nameIeee;
	  file = new File(a);
	  file.createNewFile();
	  pw = new PrintWriter(new FileOutputStream(a));
   	  pw.println(processBibFiles(saveFile,author,"ieee"));
	  
	  a = nameAcm;
	  file = new File(a);
	  file.createNewFile();
	  pw = new PrintWriter(new FileOutputStream(a));
   	  pw.println(processBibFiles(saveFile,author,"acm"));
	  
   	  a = nameNj;
	  file = new File(a);
	  file.createNewFile();	
	  pw = new PrintWriter(new FileOutputStream(a));
   	  pw.println(processBibFiles(saveFile,author,"nj"));
    }
	
	catch(FileNotFoundException e) 
	{
	  sc.close();
	  System.out.println("Could not open input file "+a+" forreading.\n");
	  System.out.println("Please check if file exists! Program will terminates after closing any opened files");
	  System.exit(0); 
	}
	
	
	catch(IOException e)
	{  
		System.out.println(a +" can not be created.");
		file = new File(nameIeee);  if(file.exists()) file.delete();
		file = new File(nameAcm);   if(file.exists()) file.delete();
		System.exit(0);
	}
	
	catch(FileExsitsException e)
	{
	   for(File filess : list)
	   {   if(filess.getName().equals(nameIeeeBU))
	        {filess.delete();}
	       
	       else if(filess.getName().equals(nameAcmBU))
	        {filess.delete();}
           
	       else if(filess.getName().equals(nameNjBU))
            {filess.delete();}
	         
	       else if(filess.getName().equals(nameIeee))
	       { File oldfile = new File(nameIeee);
	         File newfile = new File(nameIeeeBU);
	         oldfile.renameTo(newfile);
	         System.out.println("A file already exists with the name: "+nameIeee+"\nFile will be renamed as: "+nameAcmBU+" and any old BUs will be deleted.");
	       }   
	       
	       else if(filess.getName().equals(nameAcmBU))
           { File oldfile = new File(nameAcm);
	         File newfile = new File(nameAcmBU);
	         oldfile.renameTo(newfile);
	       System.out.println("A file already exists with the name: "+nameAcm+"\nFile will be renamed as: "+nameAcmBU+" and any old BUs will be deleted.");
           } 
	       
	       else if(filess.getName().equals(nameNjBU)) 
           { File oldfile = new File(nameNj);
	         File newfile = new File(nameNjBU);
	         oldfile.renameTo(newfile);
	       System.out.println("A file already exists with the name: "+nameNj+"\nFile will be renamed as: "+nameNjBU+" and any old BUs will be deleted.");
           }
        
	   }
	       
	}
	
	key.close();
	
	
	
	}

}
