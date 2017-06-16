/**
 * Created by asiebert on 08.06.2017.
 */

module.exports = function( src, where, then, error ) {
    var jq = require('node-jq');
    var sync = require( 'synchronize' );
    var _ = require( 'underscore' );

    if( _.isUndefined( src )
        || _.isNull( src )
    ) {
        // console.error( "> no params! please fix." );
        return [];
    }

    if( _.isUndefined( where )
        || _.isNull( where )
    ) {
        var rval = [];
        rval.push( src );

        return rval;
    }

    /*
    var x;
    sync.fiber( function() {

        //

        return sync.await(

            function () { */
    jq.run(where, src, {
        input: 'json',
        output: 'json'
    })
    .then(function (output) {
        console.log(output);
        then( output );
    })
    .catch(function (err) {
        console.error(err);
        error(err);
    });
            //}
            /*,
            sync.defer());
            */
    //});

};
