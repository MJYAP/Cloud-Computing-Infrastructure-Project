//Produce of import library list 

val findImport = files.filter(line => line contains "import") 

.distinct() 

.sortBy(identity) 

val libraryList = findImport.collect 

var libraryName = "" 

libraryList.foreach(c => libraryName = libraryName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/libraryList.txt",libraryName) 

//Calculate number of library 

val numberOfLibrary = findImport.count 

codeMetrics = codeMetrics + "Number of Library\t\t\t" + numberOfLibrary + "\n"