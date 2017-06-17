/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;

describe("Process examples", function() {

        describe("process - example 1", function () {

            it("handle initialization.", function (done) {
                var load = require('../impl/load');
                var select = require( '../impl/select');

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

            });



            it("SELECT - TRANSFORM", function (done) {
                var load = require('../impl/load');
                var each = require( '../impl/select');

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
                        each(".Invoices"),
                            /*.each()
                            .transform( function(node) {
                                return {
                                    val : node.value
                                }
                            }),*/
                        function (nodes) {
                            console.log("nodes selected: ");
                            console.log(nodes);

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