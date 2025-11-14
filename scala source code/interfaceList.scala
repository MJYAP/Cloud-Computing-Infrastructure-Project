//Produce of interface list 

val findInterface = files.filter(line => line contains "public interface") 

.filter(!_.contains("*")) 

.map(_.split(" ")) 

.map(_(2)) 

val listInterface = findInterface.collect 

var interfaceName = "" 

listInterface.foreach(c => interfaceName = interfaceName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/interfaceList.txt",interfaceName) 

//Calculate number of interface 

val numberOfInterface = findInterface.count 

codeMetrics = codeMetrics + "Number of Interface\t\t\t" + numberOfInterface + "\n"