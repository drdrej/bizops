/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;

var deps = require('../impl/dependencies');


describe("Process examples", function() {

        describe("process - example 1", function () {

            it("handle initialization.",
                function (done) {
                deps( function( ) {

                    var input = {
                        "Invoices" : [
                            {
                              value : 10
                            },
                            {
                                value : 22
                            }
                        ],
                        name: "Test"
                    };

                    load(input)
                        .process( select(".Invoices"),
                            function (nodes) {
                               console.log("nodes selected: ");
                               console.log(nodes);

                               done();
                        }
                    );
                })
            });

            it("can select.all", function (done) {
                var input = {
                    "Invoices" : [
                        {
                            value : 10
                        },
                        {
                            value : 22
                        }
                    ],
                    name: "Test"
                };

                load(input)
                    .process(
                        select(".Invoices").all(),
                        function ( result ) {
                            console.log("nodes selected: ");
                            console.log( result );

                            assert.typeOf( result, "array", "return value should be an array" );
                            assert.lengthOf( result, 2, "return value should be empty" );

                            done();
                        }
                    );

            });

        });

    });



/*.
 create({
 value: " Hello my nam is {{name}}."
 })

 .
 dump().
 then().
 error()*/