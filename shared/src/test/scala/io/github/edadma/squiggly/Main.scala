package io.github.edadma.squiggly

import io.github.edadma.datetime.Datetime
import pprint.pprintln

import scala.language.postfixOps

object Main extends App {

  case class Person(name: String, age: Int)

  //  val data = Map("date" -> Datetime.now())
  //  val data = Map("jonny" -> Person("jonny", 45))
  val data = Map("x" -> 5)
  //  val data = List(BigDecimal(3), BigDecimal(4), List(BigDecimal(7), BigDecimal(5)), BigDecimal(6))
  //  val data = Datetime.now()
  //  val input = "zxcv {{ with .jonny -}} name: {{ .name }} age: {{ .age }} {{- end }} asdf "
  //  val input = "{{ .date.unix }}"
  val input = "{{ partial '' {x :5} }}{{ return 'asdf' }}"
  val parser = TemplateParser.simple(input)
  val ast = parser.parse

  //  pprintln(ast)
  val partial =
    """
      |{{ return .x + 2 }}
      |""".trim.stripMargin
  val partialTemplate = TemplateParser.simple(partial).parse
  val ret = Renderer.simple(_ => Some(partialTemplate)).render(data, ast)

  println
  println(ret)

}

// todo: https://pkg.go.dev/text/template#hdr-Arguments
