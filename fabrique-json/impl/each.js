/**
 * Created by asiebert on 08.06.2017.
 */
var select = require( "./select-jq" );
var load = require('load-json-file');

module.exports = function( where ) {
    var _ = require( "underscore" );
    // var S = require( "string" );

    return {
        query : where,

        process : function( input, then, error ) {
            if( _.isUndefined( where ) ) {
                then(input);
                return;
            }

            if( _.isString( where ) ) {
                select( input, where, then );
                return;
            }

            if( _.isFunction( where ) ) {
                then( where(input));
                return;
            }

            if( _.isObject( where ) ) {
//                return function() {
                    var src = null;

                    if( _.has( where, "file" ) ) {
                        src = load.sync( where.file );
                    } else {
                        src = load.sync( input );
                    }

                    if( _.has( where, "query" ) ) {
                        select( src, where.query, then);
                        return;
                    } else {
                        then( src );
                        return;
                    }
//                };
            }

            error( "Can't understand where-Clause: " + where );
        }
    };
};