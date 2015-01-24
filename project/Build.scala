import sbt._
import Keys._

import sbtassembly._
import Plugin._
import AssemblyKeys._


object ProjectBuild extends Build {

	def createProject(projectName: String, ver: String) : Project = {
		Project(
			id = projectName,
			base = file(projectName),
			settings = BaseSettings ++ Seq(
				name := projectName,
				version := ver
			)
		)
	}

	// GLOBAL SETTINGS/DEPENDENCIES /////////
	val BaseSettings = Project.defaultSettings ++ assemblySettings ++ Seq(
		organization := "com.ua",
		scalaVersion := "2.11.2"
	)

	val loggingDeps = List(
		"com.typesafe.scala-logging" %% "scala-logging" % "3.0.0"
	)

	val testingDeps = List(
		"org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
		"org.specs2" %% "specs2" % "2.4.2" % "test",
		"org.elasticsearch" % "elasticsearch" % "1.3.2" % "test",
		"org.scalatest" % "scalatest_2.11" % "2.2.2" % "test"
	)

	val BaseDeps = loggingDeps ++ testingDeps

	// ROOT PROJECT /////////////////////////

	lazy val root = createProject("root", "0.0.1") aggregate  ( app1, app2)

	// PROJECT 1 /////////////////////////////

	lazy val app1Deps = List() ++ BaseDeps
	lazy val app1 = createProject("app1", "0.0.1").settings( libraryDependencies ++= app1Deps )

	// PROJECT 2 /////////////////////////////

	lazy val app2Deps = List() ++ BaseDeps
	lazy val app2 = createProject("app2", "0.0.1").settings( libraryDependencies ++= app2Deps )

}


