// Produce number of constant variables 

val findConstantVariable = files.filter(line => line.contains("final")) 

  .map(_.trim) 

  .distinct() 

  .sortBy(identity) 

// Collect constant variables and save to file 

val listConstantVariable = findConstantVariable.collect 

var constantVariableName = "" 

listConstantVariable.foreach(c => constantVariableName = constantVariableName + c + "\n") 

dbutils.fs.put("/FileStore/tables/data/constantVariableList.txt", constantVariableName) 

// Calculate number of constant variables 

val numberOfConstantVariable = findConstantVariable.count 

// Update codeMetrics with the number of constant variables 

codeMetrics = codeMetrics + "Number of Constant Variable\t\t\t" + numberOfConstantVariable + "\n" 

// Print codeMetrics for reference 

println(codeMetrics) 