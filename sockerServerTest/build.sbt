import sbt._
import Process._
import Keys._

name := "SocketServerDemo"
version := "1.0"
scalaVersion := "2.10.4"
retrieveManaged :=true
javaOptions ++= Seq("-source", "1.7")
compileOrder in Compile := CompileOrder.JavaThenScala
compileOrder in Test := CompileOrder.Mixed
