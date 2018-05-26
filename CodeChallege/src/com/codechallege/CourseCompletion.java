/**
 * A program that, given a list of courses and their pre-requisites, produces a possible order in which you may complete as many of the provided course units as possible, adhering to the pre-requisite requirements.
 * It reads data from two files
 * Courses.csv : List of courses and its description
 * prerequisites.csv :  List of course ids and its prerequisites
 *  
 * @author Janice Mcenroe
 */



package com.codechallege;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


	//Function to find the least dependent(i.e zero dependent) and removing from the prerequisites of other course ids after it moves to completed list  the id search through DFS
	class Recursive_Checking {
		
		
		public void recursion(Map<Integer,HashSet<Integer>> cp,Map<Integer,HashSet<Integer>> pc, Map<Integer,Integer> precount,List<Integer> completedLoop,List<Integer> finalcomplete)
				{
				 
			    List<Integer> completed = new ArrayList<Integer>();
			     
				 for (Integer l : completedLoop)
						 {
							 Set<Integer> remove = new HashSet<Integer>();
							 remove.add(l);
							 if((pc.get(l))!=null)
								 {
								 Set<Integer> CourseIdDependent = pc.get(l);
								 if(!CourseIdDependent.isEmpty())
									 {
									  for(Integer pc1:CourseIdDependent)
										 {
											cp.get(pc1).removeAll(remove);
											Integer currcount = precount.get(pc1);
											if(currcount > 0)
												{
													currcount = currcount - 1;
													precount.put((Integer)pc1,currcount);
													if(currcount <= 0)
														{
															completed.add((Integer)pc1);
														}
													
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
						
			{
				 //Initialization of variables
				/*	courseList  	: holds the list of courses with the course ids
				  * cp				: holds the map of courses id and its corresponding prerequisites
				  * pc      		: holds the map of prerequisites id and its corresponding courses to easily reverse map the courses to avoid unnecessary looping
				  * precount		: holds the number of prerequisites for each course id to find the least dependent course
				  * noPreReq		: holds the set of courses id with no prerequisites
				  * completed   	: holds the list of temporary completed courses with zero prerequisites which is after moved to Final complete 
				  * completedLoop   : used for looping through the temporary completed list (can use cloning also)
				  * finalcomplete	: holds the list of final completed courses with zero prerequisites  
				  */
				
				 Map<Integer,String> courseList = new HashMap<Integer,String>();
				 Map<Integer,HashSet<Integer>> cp = new HashMap<Integer,HashSet<Integer>>();
				 Map<Integer,Integer> precount = new HashMap<Integer,Integer>();
				 Map<Integer,HashSet<Integer>> pc = new HashMap<Integer,HashSet<Integer>>();
				 Set<Integer>  noPreReq = new HashSet<Integer>();
			
				 List<Integer> completed = new ArrayList<Integer>();
			 	 List<Integer> completedLoop = new ArrayList<Integer>();
				 List<Integer> finalcomplete = new ArrayList<Integer>();
			
				
						 try {
					
					         //File f = new File("src/com/codechallege/courses.csv");
							 Scanner scanner = new Scanner(System.in);
							 System.out.print("Enter courses file name: ");
							 System.out.flush();
							 String filename = scanner.nextLine();
							 File f = new File(filename);
							// File f = new File("src/com/codechallege/courses.csv");
							  BufferedReader b = new BufferedReader(new FileReader(f));
					        
					         String readLine = "";
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
				 
				
					
						noPreReq.addAll(courseList.keySet());
						try {
							 
							    //Read prerequisites From prerequisites.csv  file
					           // File f = new File("src/com/codechallege/prerequisites.csv");
							 Scanner scanner = new Scanner(System.in);
							 System.out.print("Enter the prerequisites file name: ");
							 System.out.flush();
							 String filename = scanner.nextLine();
							 File f = new File(filename);
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
								    			  if(precount.get(c)>0)
								    			  {
								    				  precount.put(c,(precount.get(c)+1));
								    			  }
						    				 }
					    			  
					    			}	
					    		
					            }
						 }
			
					         catch (IOException e) {
					            e.printStackTrace();
					         }
					
						 noPreReq.removeAll(cp.keySet());
						 completedLoop.addAll(noPreReq);
						 
						 //Calling recursive function
						 Recursive_Checking rec = new Recursive_Checking();
						 rec.recursion(cp,pc, precount, completedLoop,finalcomplete);
						 
						    for(Integer cdlist:finalcomplete)
							 {
									 System.out.println(cdlist+" , "+courseList.get(cdlist));
							 }
						
			}
   }