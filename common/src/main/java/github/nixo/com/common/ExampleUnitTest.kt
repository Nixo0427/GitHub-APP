package github.nixo.com.common

class ExampleUnitTest {


    fun testBoolen(){
        val result = getABoolean().yes{

        }.otherwise {
            2
        }



        }


    fun getABoolean() = true

}