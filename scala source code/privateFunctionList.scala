// Produce number of private functions 

val findPrivateFunction = files.filter(line => line.contains("private")) 

  .filter(_.contains("(")) 

  .filter(!_.contains("final")) 

  .filter(!_.contains(";")) 

  .map(_.replaceAll("[{}]", "")) 

  .map(_.trim) 

  .distinct() 

  .sortBy(identity) 

 

// Collect private functions and save to file 

val listPrivateFunction = findPrivateFunction.collect 

var privateFunctionName = "" 

listPrivateFunction.foreach(c => privateFunctionName = privateFunctionName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/privateFunctionList.txt", privateFunctionName) 

 

// Calculate number of private functions 

val numberOfPrivateFunction = findPrivateFunction.count 

 

// Update codeMetrics with the number of private functions 

codeMetrics = codeMetrics + "Number of Private Function\t\t\t" + numberOfPrivateFunction + "\n" 

 

// Print codeMetrics for reference 

println(codeMetrics) 