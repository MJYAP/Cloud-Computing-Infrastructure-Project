// Define a regular expression to match variable declarations 

val variableDeclarationPattern = """^\s*([a-zA-Z0-9_<>]+)\s+([a-zA-Z0-9_]+)\s*(=.*?)?;\s*$""".r 

// Produce number of default variables 

val findDefaultVariable = files.flatMap { 

                          case variableDeclarationPattern(dataType, variableName, _) => Some(s"${dataType.trim} ${variableName.trim};") 

                          case _ => None 

                          } 

                          .filter(!_.contains("return")) 

                          .map(_.replaceAll(";", "")) 

                          .map(_.trim) 

                          .distinct() 

                          .sortBy(identity) 

 

// Collect default variables and save to file 

val listDefaultVariable = findDefaultVariable.collect 

var defaultVariableName = "" 

listDefaultVariable.foreach(c => defaultVariableName = defaultVariableName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/defaultVariableList.txt", defaultVariableName) 

 

// Calculate number of default variables 

val numberOfDefaultVariable = findDefaultVariable.count 

 

// Update codeMetrics with number of default variables 

codeMetrics = codeMetrics + "Number of Default Variable\t\t\t" + numberOfDefaultVariable + "\n" 