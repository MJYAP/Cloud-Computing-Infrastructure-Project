// Load files 

val files = sc.textFile("/FileStore/LiveMart/*") 

// Produce number of public functions 

val findPublicFunction = files.filter(line => line.contains("public")) 

  .filter(!_.contains("class")) 

  .filter(!_.contains("interface")) 

  .filter(_.contains("(")) 

  .map(_.replaceAll("[{,;]", "")) 

  .map(_.trim) 

  .distinct() 

  .sortBy(identity) 

// Collect public functions and save to file 

val listPublicFunction = findPublicFunction.collect 

var publicFunctionName = "" 

listPublicFunction.foreach(c => publicFunctionName = publicFunctionName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/publicFunctionList.txt", publicFunctionName) 

// Calculate number of public functions 

val numberOfPublicFunction = findPublicFunction.count 

// Update codeMetrics with the number of public functions 

var codeMetrics = "" 

codeMetrics = codeMetrics + "Number of Public Function\t\t\t" + numberOfPublicFunction + "\n" 

// Print codeMetrics for reference 

println(codeMetrics) 