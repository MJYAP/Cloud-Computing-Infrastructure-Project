import org.apache.hadoop.fs.{FileSystem,Path} 

import scala.collection.mutable.ArrayBuffer 

var privateVariableList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var publicVariableList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var constantVariableList =new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var interfaceList = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

var abstractClassList = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

var libraryList = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

var privateFunctionList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var publicFunctionList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var defaultVariableList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var protectedFunctionList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var classList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

var javaProjectList = new ArrayBuffer[(String, String, 

scala.collection.mutable.ArrayBuffer[String])]() 

 

def findPrivateVariables(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName;  

val data = sc.textFile(filename.getPath.toString) 

val findPrivateVariable = data.filter(line => line contains "private" ) 

.filter(!_.contains("(")) 

.filter(!_.contains("//")) 

.filter(!_.contains("final")) 

.map(_.replaceAll(";","")) 

.map(_.trim) 

val functionArray = new ArrayBuffer[String]() 

findPrivateVariable.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"PrivateVariables",functionArray) 

total_RDD.append(resultRDD) 

}) 

privateVariableList = total_RDD 

} 

 

def findPublicVariables(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findPublicVariable = data.filter(line => line contains "public" ) 

.filter(!_.contains("(")) 

.filter(_.contains(";")) 

.map(_.replaceAll(";","")) 

.map(_.trim) 

val functionArray = new ArrayBuffer[String]() 

findPublicVariable.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"PublicVariables",functionArray) 

total_RDD.append(resultRDD) 

}) 

publicVariableList = total_RDD 

} 

 

def findConstantVariables(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findConstantVariable = data.filter(line => line contains "final" ) 

.map(_.trim) 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findConstantVariable.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"ConstantVariables",functionArray) 

total_RDD.append(resultRDD) 

}) 

constantVariableList = total_RDD 

} 

 

def findInterfaceList(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 
var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findInterface = data.filter(line => line contains "public interface") 

.filter(!_.contains("*")) 

.map(_.split(" ")) 

.map(_(2)) 

val functionArray = new ArrayBuffer[String]() 

findInterface.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"InterfaceList",functionArray) 

total_RDD.append(resultRDD) 

}) 

interfaceList = total_RDD 

} 

 

def findAbstractClassList(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findAbstract = data.filter(line => line.contains("extends") || line.contains("implements")) 

.map(_.trim) 

.filter(!_.contains("*")) 

.map(_.replaceAll("[{]","")) 

.distinct() 

val functionArray = new ArrayBuffer[String]() 

findAbstract.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"AbstractClassList",functionArray) 

total_RDD.append(resultRDD) 

}) 

abstractClassList = total_RDD 

} 

 

def findLibraryList(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findLibrary = data.filter(line => line contains "import") 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findLibrary.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"LibraryList",functionArray) 

total_RDD.append(resultRDD) 

}) 

libraryList = total_RDD 

} 

 

def findPrivateFunctionList(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findPrivateFunction = data.filter(line => line contains "private" ) 

.filter(_.contains("(")) 

.filter(!_.contains("final")) 

.filter(!_.contains(";")) 

.map(_.replaceAll("[{}]","")) 

.map(_.trim) 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findPrivateFunction.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"PrivateFunctionList",functionArray) 

total_RDD.append(resultRDD) 

}) 

privateFunctionList = total_RDD 

} 

 

def findPublicFunctionList(files: Array[org.apache.hadoop.fs.FileStatus]) =  

{ 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]()  

files.foreach(filename => { 

val currentFile = filename.getPath.getName; 

val data = sc.textFile(filename.getPath.toString) 

val findPublicFunction = data.filter(line => line contains "public" ) 

.filter(!_.contains("class")) 

.filter(!_.contains("interface")) 

.filter(_.contains("(")) 

.map(_.replaceAll("[{,;]","")) 

.map(_.trim) 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findPublicFunction.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile,"PublicFunctionList",functionArray) 

total_RDD.append(resultRDD) 

}) 

publicFunctionList = total_RDD 

} 

 

def findDefaultVariables(files: Array[org.apache.hadoop.fs.FileStatus]) = { 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

files.foreach(filename => { 

val currentFile = filename.getPath.getName 

val data = sc.textFile(filename.getPath.toString) 

// Filter lines containing variable declarations with default values 

val findDefaultVariable = data.filter(line => line.contains("=") && !line.contains("==")) 

.map(_.trim) 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findDefaultVariable.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile, "DefaultVariables", functionArray) 

total_RDD.append(resultRDD) 

}) 

defaultVariableList = total_RDD 

} 

 

def findProtectedFunctions(files: Array[org.apache.hadoop.fs.FileStatus]) = { 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

files.foreach(filename => { 

val currentFile = filename.getPath.getName 

val data = sc.textFile(filename.getPath.toString) 

// Filter lines containing protected function declarations 

val findProtectedFunction = data.filter(line => line.contains("protected") && line.contains("(")) 

.map(_.replaceAll("[{}]","").trim) // Remove extra characters and trim 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findProtectedFunction.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile, "ProtectedFunctions", functionArray) 

total_RDD.append(resultRDD) 

}) 

protectedFunctionList = total_RDD 

} 

 

def findClasses(files: Array[org.apache.hadoop.fs.FileStatus]) = { 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

files.foreach(filename => { 

val currentFile = filename.getPath.getName 

val data = sc.textFile(filename.getPath.toString) 

// Filter lines containing class declarations 

val findClasses = data.filter(line => line.contains("class") && line.contains("{")) 

.map(_.replaceAll("[{};]","").trim) // Remove extra characters and trim 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findClasses.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile, "Classes", functionArray) 

total_RDD.append(resultRDD) 

}) 

classList = total_RDD 

} 

 

def findJavaProjects(files: Array[org.apache.hadoop.fs.FileStatus]) = { 

var total_RDD = new ArrayBuffer[(String, String, scala.collection.mutable.ArrayBuffer[String])]() 

files.foreach(filename => { 

val currentFile = filename.getPath.getName 

val data = sc.textFile(filename.getPath.toString) 

// Filter lines containing Java project structure patterns 

val findJavaProjects = data.filter(line =>  

line.contains("public class") || // Java classes 

line.contains("package ") || // Package declarations 

line.contains("import ") || // Import statements 

line.contains("public static void main(") // Main method declarations 

) 

.map(_.trim) 

.distinct() 

.sortBy(identity) 

val functionArray = new ArrayBuffer[String]() 

findJavaProjects.collect.foreach(line => functionArray.append(line)) 

val resultRDD = (currentFile, "JavaProjects", functionArray) 

total_RDD.append(resultRDD) 

}) 

javaProjectList = total_RDD 

} 

 

def runProgram(): Unit = { 

 

// Load files and perform analysis 

val files = FileSystem.get(sc.hadoopConfiguration).listStatus(new Path("/FileStore/LiveMart")) 

findPrivateVariables(files) 

findPublicVariables(files) 

 

// Process files and collect output 

val output = privateVariableList.mkString("\n") + 

"\n" + 

publicVariableList.mkString("\n") + 

"\n" + 

constantVariableList.mkString("\n") + 

"\n" + 

interfaceList.mkString("\n") + 

"\n" + 

abstractClassList.mkString("\n") + 

"\n" + 

libraryList.mkString("\n") + 

"\n" + 

privateFunctionList.mkString("\n") + 

"\n" + 

publicFunctionList.mkString("\n") 

 

// Write the output to a file 

dbutils.fs.put("/FileStore/tables/data/searchComponentsList.txt", output, true) 

// Close FileSystem 

FileSystem.closeAll() 

} 

 

runProgram() 

FileSystem.closeAll 