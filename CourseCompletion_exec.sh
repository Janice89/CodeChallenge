#/usr/bin/ksh

#-----  Shell Script to compile and run the java script ----- 
#-----  Created for Code Challenge Project ---------
#----- Created by Janice on 25 May 2018  -------

echo 'The Script Name is:' ${0} ;
echo '---------------------------'
echo 'You have to enter the filename with location for the code to run'

echo 'Compiling the code CourseCompletion.java ........'
javac CourseCompletion.java 
echo 'The return code is ' ${?}

echo 'Executing......... '
java CourseCompletion 

echo 'You have to follow the  above order to complete maximum number of courses'
echo '-------------------------------------------------------------------------'



