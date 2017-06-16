/**
 * Created by asiebert on 08.06.2017.
 */

module.exports = function( src, where, then, error ) {
    var jq = require('node-jq');
    var sync = require( 'synchronize' );
    var _ = require( 'underscore' );

    var callback = require( './callback' );

    if( _.isUndefined( src )
        || _.isNull( src )
    ) {
        callback( then ).exec([]);
        return;
    }

    if( _.isUndefined( where )
        || _.isNull( where )
    ) {
        var rval = [];
        rval.push( src );

        callback(then).exec(rval);
        return;
    }

    jq.run(where, src, {
        input: 'json',
        output: 'json'
    })
    .then(function (output) {
        console.log(output);
        var rval = (_.isNull( output) ?  [] : output);

        callback(then).exec( output );

        return rval;
    })
    .catch(function (err) {
        console.error(err);
        callback(error).exec(err);

        return null;
    });

};
