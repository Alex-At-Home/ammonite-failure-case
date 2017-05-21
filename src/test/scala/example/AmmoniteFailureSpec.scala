package example

import org.scalatest._
import example.Traits._

class AmmoniteFailureSpec extends FlatSpec with Matchers {

    "Ammonite" should "pass" in {
        // First off check that the code runs fine outside of the script:
        
        case class Dummy(anotherParam: String) extends MiscTrait
        val d = Dummy("dummy")
        case class Internal(param0: MiscTrait) extends BaseTrait
        val test = Internal(d)
        test.showAnotherParam
        
        test.showAnotherParam should be("dummy")
    }
    "Ammonite" should "fail" in {

        // Now try the same thing using the Ammonite shell:

        val sampleFailureCode0 = """
            case class Dummy(anotherParam: String) extends MiscTrait
            val d = Dummy("dummy")
        """

        val sampleFailureCode1 = """
            case class Internal(param0: MiscTrait) extends BaseTrait
        """

        val sampleFailureCode2 = """
            val test = Internal(d)
            test.showAnotherParam
        """

        val startupRun = s"""
            //Enter the following code to fail:

            $sampleFailureCode0

            //then:

            $sampleFailureCode1
            
            //then:
            
            $sampleFailureCode2

            //observe failure, then:

            exit
        """

        val startupInfo = s"""
            import example.Traits._

            println(s\"\"\"
                $startupRun
                \"\"\")

        """.stripMargin

        // Fails if you paste the code in:
        // val shell = ammonite.Main(predef = startupInfo)
        // shell.run()

        // Will fail, see output:
        val inStream = new java.io.ByteArrayInputStream(
            startupRun.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        )
        val errStream = new java.io.ByteArrayOutputStream()
        val shell = ammonite.Main(predef = startupInfo, inputStream = inStream, errorStream = errStream)
        shell.run()
        val result = errStream.toString("UTF-8")
        result shouldNot include("AbstractMethodError")
        println(s"Errors:\n$result")

        // Works:
        //shell.runCode(startupRun)

        // Works with replApi = true or false
        //val interp = shell.instantiateInterpreter(replApi = true)
        //interp.interpApi.load(sampleFailureCode0)
        //interp.interpApi.load(sampleFailureCode1)
        //interp.interpApi.load(sampleFailureCode2)
    }
}
