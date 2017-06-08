/**
 * Created by asiebert on 08.06.2017.
 */

module.exports = function( src, where ) {
    var jq = require('node-jq');
    var sync = require( 'synchronize' );
    var _ = require( 'underscore' );

    if( _.isUndefined( src )
        || _.isNull( src )
    ) {
        // console.error( "> no params! please fix." );
        return [];
    }

    if(
        _.isUndefined( where )
        || _.isNull( where )
    ) {
        var rval = [];
        rval.push( src );
        return rval;
    }

    return sync.await(
        function( ) {
            jq.run( where, src,  {
                input: 'json',
                output: 'json'
            })
                .then( function(output)  {
                    console.log(output);
                })
                .catch(function(err)  {
                    console.error(err);
                })
        },
        sync.defer());




    /*
     const filter = '.abilities[].moves'
     const jsonPath = '/path/to/bulbasaur.json'
     const options = {}


     */


};
