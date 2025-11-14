def runSearch(): Unit = { 

val selectedClass = dbutils.widgets.get("Class") 

val selectedComponent = dbutils.widgets.get("Select Component") 

val componentList = ArrayBuffer("PrivateVariables", "PublicVariables", "ConstantVariables", "LibraryList", "PrivateFunctionList", "PublicFunctionList", "InterfaceList", "AbstractList") 

 

if (componentList.contains(selectedComponent)) { 

val listPointer = selectedComponent match { 

case "PrivateVariables" => privateVariableList 

case "PublicVariables" => publicVariableList 

case "ConstantVariables" => constantVariableList 

case "LibraryList" => libraryList 

case "PrivateFunctionList" => privateFunctionList 

case "PublicFunctionList" => publicFunctionList 

case "DefaultVariables" => defaultVariableList 

case "ProtectedFunction" => protectedFunctionList 

case "InterfaceList" => interfaceList 

case "AbstractClassList" => abstractClassList 

} 

 

println("====================") 

for (row <- listPointer) { 

if (row._1 == s"$selectedClass.java") { 

if (row._3.nonEmpty) { 

// Iterate over the components and print them, handling the case of duplicated 'id' 

row._3.foreach { component => 

val trimmedComponent = component.trim 

if (trimmedComponent.startsWith("public final Long id")) { 

val enclosingClassName = selectedClass match { 

case "SocketStatsWithId" => "SocketStatsWithId" 

case "ServerStatsWithId" => "ServerStatsWithId" 

case _ => "Unknown" 

} 

println(s"${enclosingClassName}.$trimmedComponent") 

} else { 

println(trimmedComponent) 

} 

} 

} else { 

println("Not Found") 

} 

} 

} 

println("====================") 

} else { 

println("Invalid component selected.") 

} 

} 

 

runSearch() 