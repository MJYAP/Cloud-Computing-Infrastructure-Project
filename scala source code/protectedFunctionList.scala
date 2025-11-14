// Produce number of protected functions 

val findProtectedFunction = files.filter(line => line.contains("protected")) 

  .filter(_.contains("(")) 

  .filter(!_.contains("final")) 

  .filter(!_.contains(";")) 

  .map(_.replaceAll("[{}]", "")) 

  .map(_.trim) 

  .distinct() 

  .sortBy(identity) 

 

// Collect protected functions and save to file 

val listProtectedFunction = findProtectedFunction.collect 

var protectedFunctionName = "" 

listProtectedFunction.foreach(c => protectedFunctionName = protectedFunctionName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/protectedFunctionList.txt", protectedFunctionName) 

 

// Calculate number of protected functions 

val numberOfProtectedFunction = findProtectedFunction.count 

 

// Update codeMetrics with the number of protected functions 

codeMetrics = codeMetrics + "Number of Protected Function\t\t\t" + numberOfProtectedFunction + "\n"