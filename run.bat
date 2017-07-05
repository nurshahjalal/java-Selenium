set projectLocation=C:\JenkinsWorkspace\V7\Sandata_v1
cd %projectLocation%
SET CLASSPATH=C:\Users\%USERNAME%\.m2\repository\org\testng\testng\6.9.10\testng-6.9.10.jar;
mvn clean test -DsuiteXmlFile=%projectLocation%\pom.xml