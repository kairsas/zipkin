import io.zipkin.sbt._

defaultSettings

assemblyMergeStrategy in assembly := {
  case PathList("org", xs @ _*) => MergeStrategy.first
  case PathList("com", xs @ _*) => MergeStrategy.first
  case "BUILD" => MergeStrategy.first
  case PathList(ps @_*) if ps.last == "package-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

BuildProperties.buildPropertiesPackage := "com.twitter.zipkin"

resourceGenerators in Compile <+= BuildProperties.buildPropertiesWrite
