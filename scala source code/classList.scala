// Load files 

val files = sc.textFile("/FileStore/LiveMart/*") 

// Define codeMetrics header 

var codeMetrics = "\nMetric\t\t\t\t\tCount\n" 

// Find classes 

val findClass = files.filter(line => line.contains("public class")) 

  .filter(!_.contains("*")) 

  .map(_.split(" ")) 

  .map(_(2)) 

// Collect class names 

val listClass = findClass.collect() 

// Concatenate class names 

var className = "" 

listClass.foreach(c => className = className + c + "\n") 

// Save class list to file 

dbutils.fs.put("/FileStore/tables/data/classList.txt", className) 

// Calculate number of classes 

val numberOfClass = findClass.count() 

// Update codeMetrics with the number of classes 

codeMetrics += s"Number of Classes\t\t\t$numberOfClass\n" 

// Print codeMetrics for reference 

println(codeMetrics) 