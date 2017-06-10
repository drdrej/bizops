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

        process : function( input ) {
            if( _.isUndefined( where ) ) {
                return input;
            }

            if( _.isString( where ) ) {
                return select( input, where );
            }

            if( _.isFunction( where ) ) {
                return where(input);
            }

            if( _.isObject( where ) ) {
                return function() {
                    var src = null;

                    if( _.has( where, "file" ) ) {
                        src = load.sync( where.file );
                    } else {
                        src = load.sync( input );
                    }

                    if( _.has( where, "query" ) ) {
                        return select( src, where.query );
                    } else {
                        return src;
                    }
                };
            }

            throw "Can't understand where-Clause: " + where;
        }
    };
};