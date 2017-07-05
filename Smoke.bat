set projectLocation=%WORKSPACE%\Sandata_v8
cd %projectLocation%
SET CLASSPATH=C:\Users\%USERNAME%\.m2\repository\org\testng\testng\6.9.10\testng-6.9.10.jar;
mvn clean test -f Smoke_Pom.xml