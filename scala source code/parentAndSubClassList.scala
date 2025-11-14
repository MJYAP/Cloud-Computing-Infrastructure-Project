//Filter the extend classes out 

val file=sc.textFile("/FileStore/tables/data") 

val split=file.flatMap(x=>x.split(",")).map(x=>x.trim) 

val classes=split.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("*")).filter(!_.contains("**")).filter(_.contains("class ")) 

val classes_distinct=classes.distinct 

val extendsClass = classes_distinct.filter(_.contains("extends")) 

val firstExtendsFilterClass=extendsClass.map(line=>line.replace("public","").replace("class","").replace("{","").replace("}","").replace("interface","")) 

val secondExtendsFilterClass=firstExtendsFilterClass.map(line=>line.replace("extends","&").replace(" ","")).map(line=>line.split("&")).map(_.reverse) 

//Filter the implement classes out 

val implementsClasses = split.filter(_.contains("implements")).filter(!_.contains("//")) 

val firstImplementsFilterClass=implementsClasses.map(line=>line.replace("public","").replace("class","").replace("{","").replace("}","").replace("interface","")) 

val secondImplementsFilterClass=firstImplementsFilterClass.map(line=>line.replace("implements","&").replace(" ","")).map(line=>line.split("&")).map(_.reverse) 

val class_child_parent = secondExtendsFilterClass ++ secondImplementsFilterClass 

val a=class_child_parent.map(x => (x(0),x(1))) 

a.collect 

val df=a.toDF("Parent Class", "Sub Class") 

df.show(100,false) 