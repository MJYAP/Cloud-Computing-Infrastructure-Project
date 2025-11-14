// Filter lines to find abstract classes 

val findAbstractClass = files 

  .filter(_.contains("class")) 

  .filter(line => line.contains("extends") || line.contains("implements")) 

  .map(_.trim) 

  .filter(!_.contains("*")) 

  .map(_.replaceAll("[{]", "")) 

  .distinct() 

 

// Collect abstract class names into an array 

val listAbstractClass = findAbstractClass.collect() 

 

// Concatenate abstract class names into a single string 

var abstractClassName = "" 

listAbstractClass.foreach(c => abstractClassName = abstractClassName + c + "\n") 

 

// Write abstract class names to a text file 

dbutils.fs.put("/FileStore/tables/data/abstractClassList.txt", abstractClassName) 

 

// Calculate number of abstract classes 

val numberOfAbstractClass = findAbstractClass.count() 

 

// Update codeMetrics with the number of abstract classes 

codeMetrics = codeMetrics + s"Number of Abstract Class\t\t\t$numberOfAbstractClass\n" 