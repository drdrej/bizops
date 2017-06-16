/**
 * Created by asiebert on 08.06.2017.
 */
var select = require( "./select-jq" );
var load = require('load-json-file');

module.exports = function( where ) {
    var _ = require( "underscore" );
    var callback = require( './callback' );
    // var S = require( "string" );

    return {
        query : where,

        process : function( input, then, error ) {
            if( _.isUndefined( where ) ) {
                callback(then).exec(input);
                return;
            }

            if( _.isString( where ) ) {
                select( input, where, then );
                return;
            }

            if( _.isFunction( where ) ) {
                callback(then).exec( where(input) );
                return;
            }

            if( _.isObject( where ) ) {
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
                        callback(then).exec( src );
                        return;
                    }
            }

            callback( error ).exec( "Can't understand where-Clause: " + where );
        }
    };
};