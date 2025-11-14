// Load files 

val files = sc.wholeTextFiles("/FileStore/LiveMart/*") 

// Define codeMetrics header 

var codeMetrics = "\nMetric\t\t\t\t\tCount\n" 

// Find java projects 

val findJavaProjects = files.filter { case (filename, content) => 

    filename.endsWith(".java") 

} 

// Collect java projects 

val listJavaProjects = findJavaProjects.collect() 

// Concatenate java projects 

var javaProject ="" 

listJavaProjects.foreach(c => javaProject = javaProject + c + "\n") 

// Save java projects list to file 

dbutils.fs.put("/FileStore/tables/data/javaProjectList.txt", javaProject) 

// Calculate total number of java projects  

val numberOfJavaProjects = findJavaProjects.count() 

// Update codeMetrics 

codeMetrics = codeMetrics + "Number of Java Projects\t\t\t" + numberOfJavaProjects + "\n" 

// Print codeMetrics for reference 

println(codeMetrics) 