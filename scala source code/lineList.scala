// Load files 

val files = sc.textFile("/FileStore/LiveMart/*") 

// Define codeMetrics header 

var codeMetrics = "\nMetric\t\t\t\t\tCount\n" 

// Find lines 

val findLines = files.filter (_.length>0) 

// Collect lines 

val listLines = findLines.collect() 

// Concatenate lines 

var lines ="" 

listLines.foreach(c => lines = lines + c + "\n") 

// Save lines list to file 

dbutils.fs.put("/FileStore/tables/data/linesList.txt", lines) 

// Calculate total number of lines 

val numberOfLine = findLines.count() 

// Update codeMetrics 

codeMetrics = codeMetrics + "Number of Lines\t\t\t\t" + numberOfLine + "\n" 

// Print codeMetrics for reference 

println(codeMetrics) 