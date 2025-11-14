import scala.util.Try 

// Produce number of private variables 

val findPrivateVariable = files.filter(line => line.contains("private")) 

  .filter(!_.contains("(")) 

  .filter(!_.contains("//")) 

  .filter(!_.contains("final")) 

  .map(_.replaceAll(";", "")) 

  .map(_.trim) 

  .distinct() 

  .sortBy(identity) 

// Collect private variables 

val listPrivateVariable = findPrivateVariable.collect 

// Define file path 

val privateVariableFilePath = "/FileStore/tables/data/privateVariableList.txt" 

// Read existing content of the file, if any 

val existingContent = Try(dbutils.fs.head(privateVariableFilePath)).getOrElse("") 

// Append new content to existing content 

val newContent = listPrivateVariable.mkString("\n") + "\n" + existingContent 

// Write updated content back to the file 

dbutils.fs.put(privateVariableFilePath, newContent, overwrite=true) 

// Calculate the number of private variables 

val numberOfPrivateVariable = findPrivateVariable.count 

// Update codeMetrics with the number of private variables 

codeMetrics = codeMetrics + "Number of Private Variable\t\t\t" + numberOfPrivateVariable + "\n" 

// Print codeMetrics for reference 

println(codeMetrics)