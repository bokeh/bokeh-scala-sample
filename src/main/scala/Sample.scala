package sample

import io.continuum.bokeh._
import math.{Pi=>pi,sin}

object Sample extends App with Tools {
    object source extends ColumnDataSource {
        val x = column(-2*pi to 2*pi by 0.1)
        val y = column(x.value.map(sin))
    }

    import source.{x,y}

    val xdr = new DataRange1d().sources(x :: Nil)
    val ydr = new DataRange1d().sources(y :: Nil)

    val plot = new Plot().x_range(xdr).y_range(ydr).tools(Pan|WheelZoom)

    val xaxis = new LinearAxis().plot(plot).location(Location.Below)
    val yaxis = new LinearAxis().plot(plot).location(Location.Left)
    plot.below <<= (xaxis :: _)
    plot.left <<= (yaxis :: _)

    val glyph = new Circle().x(x).y(y).size(5).fill_color(Color.Red).line_color(Color.Black)
    val circle = new GlyphRenderer().data_source(source).glyph(glyph)

    plot.renderers := List(xaxis, yaxis, circle)

    val document = new Document(plot)
    val html = document.save("sample.html")
    println(s"Wrote ${html.file}. Open ${html.url} in a web browser.")
    html.view()
}
