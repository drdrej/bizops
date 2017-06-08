/**
 * Created by asiebert on 07.06.2017.
 */


input( i ,
    each()       // for each node n1 {
    .each( jq1 )  //   for each
    .input( "/path/{{handlebars}}.json",
         each( ... ) ...
    ) // load json | sub selects | returns loaded json
    .object( {

    })
    .transform( fnc, opts )
    .validate( fnc, opts )
    .all()
    .all( jq2 )
    .process( fnc, opts ) // allgemeine impl zum verarbeiten eines knotens. return sich selbst einen ersatzwert oder einen fehler/exception.
    .dump( "/path/{{handlebars}}.json" )
;


// json types:


{ date( "dd.mm.YYYY" )