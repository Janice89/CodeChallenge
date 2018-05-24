package com.codechallege;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;




class Recursive_Checking {
	
	
	public void recursion(Map<Integer,HashSet<Integer>> cp,Map<Integer,HashSet<Integer>> pc, Map<Integer,Integer> precount,List<Integer> completedLoop,List<Integer> finalcomplete)
		{
		//System.out.println("ENTERED RECURSION"+completedLoop);
		List<Integer> completed = new ArrayList<Integer>();
	     
		 for (Integer l : completedLoop)
			 {
				 //System.out.println("Here"+pc.get(l));
				 HashSet remove = new HashSet<Integer>();
				 remove.add(l);
				 if((pc.get(l))!=null)
				 {
				 Set<Integer> CourseIdDependent = pc.get(l);
				 if(!CourseIdDependent.isEmpty())
				 {
				 //System.out.println("CourseIdDependent--------->"+CourseIdDependent);
				 for(Integer pc1:CourseIdDependent)
				 {
					 //System.out.println("CourseIdDependent of"+l+"is-->"+pc1);
					//System.out.println("Prereq of each courseid--->"+cp.get(pc1));
					cp.get(pc1).removeAll(remove);
					//System.out.println("Prereq of each courseid after removing completed--->"+cp.get(pc1));
					Integer currcount = precount.get(pc1);
					//System.out.println("Prereq count of each courseid before removing completed--->"+currcount);
					if(currcount > 0)
					{
						////System.out.println(currcount);
						currcount = currcount - 1;
						////System.out.println("hashcode"+(Integer)pc1);
						precount.put((Integer)pc1,currcount);
						if(currcount <= 0)
						{
							//System.out.println("hashcode"+(Integer)pc1);
							completed.add((Integer)pc1);
							//completedLoop.add((Integer)pc1);
						}
						//System.out.println("Prereq count of each courseid after removing completed--->"+precount.get(pc1));
					}
					}
				 }
				 }
				 finalcomplete.add(l);
			 }
		 while(!completedLoop.isEmpty()) {
			 completedLoop.clear();
			  completedLoop.addAll(completed);
			 recursion(cp,pc, precount, completedLoop,finalcomplete);
		 }
	}
}
public class CourseCompletion {

public static void main(String args[])  
					//Completed = 1,10;
{
	Map<Integer,String> courseList = new HashMap<Integer,String>();
	 try {

         File f = new File("src/com/codechallege/data.txt");

         BufferedReader b = new BufferedReader(new FileReader(f));

         String readLine = "";

         ////System.out.println("Reading file using Buffered Reader");

         while ((readLine = b.readLine()) != null) {
             ////System.out.println(readLine);
         
 		    String[] str = readLine.split(",");
 		    Integer cid = Integer.valueOf(str[0]);
 		    String desc = str[1];
 			if(!courseList.containsKey(cid))
 			{
 				courseList.put(cid,desc);
 			}
         }
	 }
	  catch (IOException e) {

	  }
	 
	
	/**courseList.put (2,"Distributed Computing");
	courseList.put (3,"Database //Systems");
	courseList.put (4,"Algorithms 1");
	courseList.put (5,"Algorithms 2");
	courseList.put (6,"Programming in Java");
	courseList.put (7,"Advanced Programming in Java");
	courseList.put (8,"Big Data with Apache Spark");
	courseList.put (9,"Programming in Perl");
	courseList.put (10,"Probability");
	courseList.put (11,"Scalable Machine Learning");
	courseList.put (12,"Data Structures");**/
	
	
	/**HashSet cp2 = new HashSet<Integer>();
	cp2.add(1);
	cp2.add(6);
	cp2.add(11);
	cp.put (2,cp2);
	
	HashSet cp3 = new HashSet<Integer>();
	cp3.add(1);
	cp3.add(6);
	cp3.add(7);
	cp3.add(8);
	cp3.add(12);
	cp.put (3,cp3);
	
	
	HashSet cp4 = new HashSet<Integer>();
	cp4.add(1);
	cp4.add(3);
	cp4.add(9);
	cp.put (4,cp4);
	
	HashSet cp5 = new HashSet<Integer>();
	cp5.add(1);
	cp5.add(3);
	cp5.add(4);
	cp5.add(12);
	cp.put (5,cp5);
	
	
	HashSet cp6 = new HashSet<Integer>();
	cp6.add(1);
	cp6.add(6);
	cp6.add(11);
	cp6.add(11);
	cp.put (6,cp6);*/
	
			
			Map<Integer,HashSet<Integer>> cp = new HashMap<Integer,HashSet<Integer>>();
			Map<Integer,Integer> precount = new HashMap<Integer,Integer>();
			Map<Integer,HashSet<Integer>> pc = new HashMap<Integer,HashSet<Integer>>();
			
			 
			
			Set<Integer>  noPreReq = new HashSet<Integer>();
			noPreReq.addAll(courseList.keySet());
			try {
				//Read From csv txt file
		            File f = new File("src/com/codechallege/cp.txt");

		            BufferedReader b = new BufferedReader(new FileReader(f));

		            String readLine = "";
		            while ((readLine = b.readLine()) != null) {
		            	String[] str = readLine.split(",");
		    		    Integer c = Integer.valueOf(str[0]);
		    		    Integer p = Integer.valueOf(str[1]);
		    		    if(!pc.containsKey(p))
		    		    {
		    		    	pc.put(p,new HashSet<Integer>()); 
			    			pc.get(p).add(c);
		    		    }
		    		    else
		    			{
		    				 if(pc.get(p).size()>0)
		    				 {
				    			  pc.get(p).add(c);
				    			  
				    			  
				    			 
		    				 }
	    			  
		    			}	
		    			if(!cp.containsKey(c))
			    			{
			    			cp.put(c,new HashSet<Integer>()); 
			    			cp.get(c).add(p);
			    			precount.put(c,1); 
			    			

			    			}
		    			else
			    			{
			    				 if(cp.get(c).size()>0)
			    				 {
					    			  cp.get(c).add(p);
					    			//  //System.out.println("Prereq added to "+c+"--->"+p);
					    			  
					    			  if(precount.get(c)>0)
					    			  {
					    				 // //System.out.println("Count"+c+"---->"+precount.get(c));
					    				  precount.put(c,(precount.get(c)+1));
					    			  }
			    				 }
		    			  
		    			}	
		    		
		            }
			 }

		         catch (IOException e) {
		            e.printStackTrace();
		         }
			
			 for (Map.Entry<Integer,Integer> entry : precount.entrySet())
				 {
	    			////System.out.println("Course id---->"+entry.getKey() +"---->"+entry.getValue());
				 }
			 
			 for (Map.Entry<Integer,HashSet<Integer>> entry : cp.entrySet())
			 {
    			////System.out.println("Course id---->"+entry.getKey() +"---->"+entry.getValue());
			 }
			 for (Map.Entry<Integer,HashSet<Integer>> entry : pc.entrySet())
			 {
			 ////System.out.println("Course Dependents of"+entry.getKey()+"is --->"+entry.getValue());
			 }
			 noPreReq.removeAll(cp.keySet());
			 
			 List<Integer> completed = new ArrayList<Integer>();
		     List<Integer> finalcomplete = new ArrayList<Integer>();
			 List<Integer> completedLoop = new ArrayList<Integer>();
			 //completed.addAll(noPreReq);
			// finalcomplete.addAll(noPreReq);
			 completedLoop.addAll(noPreReq);
			 
			 
			 
			
			 for (Integer l : completedLoop)
			 {
				 //System.out.println(l);
				 HashSet remove = new HashSet<Integer>();
				 remove.add(l);
				 Set<Integer> CourseIdDependent = pc.get(l);
				// //System.out.println(CourseIdDependent);
				 for(Integer pc1:CourseIdDependent)
				 {
					 //System.out.println("CourseIdDependent of"+l+"is-->"+pc1);
					//System.out.println("Prereq of each courseid--->"+cp.get(pc1));
					cp.get(pc1).removeAll(remove);
					//System.out.println("Prereq of each courseid after removing completed--->"+cp.get(pc1));
					Integer currcount = precount.get(pc1);
					//System.out.println("Prereq count of each courseid before removing completed--->"+currcount);
					if(currcount > 0)
					{
						////System.out.println(currcount);
						currcount = currcount - 1;
						////System.out.println("hashcode"+(Integer)pc1);
						precount.put((Integer)pc1,currcount);
						if(currcount <= 0)
						{
							//System.out.println("hashcode"+(Integer)pc1);
							completed.add((Integer)pc1);
							//completedLoop.add((Integer)pc1);
						}
						//System.out.println("Prereq count of each courseid after removing completed--->"+precount.get(pc1));
					
					}
				 }
				 finalcomplete.add(l);
			 }
			
			 
			Recursive_Checking rec = new Recursive_Checking();
			completedLoop.clear();
			completedLoop.addAll(completed);
			while(!completedLoop.isEmpty()) {
			 rec.recursion(cp,pc, precount, completedLoop,finalcomplete);
			}
			for(Integer cdlist:finalcomplete)
			 {
					 System.out.println(cdlist+" --- "+courseList.get(cdlist));
			 }
			
}
}