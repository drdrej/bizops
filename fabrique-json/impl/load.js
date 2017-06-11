/**
 * Created by asiebert on 10.06.17.
 */

var hamsters = require('hamsters.js');

var startOptions = {
    maxThreads: 2,
    cache: false,
    debug: true,
    persistence: false
};
hamsters.init(startOptions);

module.exports = function( input ) {

    return {
        process : function (
            selection,
            then,
            error
        ) {
            hamsters.run(input, function( ) {
                console.log( "> process: " + selection.query )
                return selection.process( this );
            }, function( output ) {
                console.log( "> process-output: " );
                console.log( output );

                var _ = require( 'underscore' );

                if( _.isFunction(then) ) {
                    then( output );
                }
            }, 1, true );
        }
    };

};