package edu.norcocollege.cis18b.week7.mini01;

import java.util.List;

public class ProcessVsThreadClinic 
{

    public static void main(String[] args) {
        for (Scenario scenario : defaultScenarios()) {
            System.out.println(scenario.name() + " -> " + scenario.recommendation());
        }
    }

    static List<Scenario> defaultScenarios() 
    {
        return List.of(

            new Scenario(
                "student-code-runner",
                "Run untrusted student code with strong isolation so crashes or malicious behavior cannot affect the main system.",
                Recommendation.PROCESS,
                "Processes use separate memory spaces. This prevents unsafe or buggy student code from corrupting the host application. The tradeoff is higher overhead for communication, but safety is more important here."
            ),

            new Scenario(
                "gradebook-auto-save",
                "Save grade updates in the background while the UI remains responsive.",
                Recommendation.THREAD,
                "Threads share memory within the same process, making it efficient to access and update grade data without copying between processes. However, shared mutable state must be protected to avoid race conditions."
            ),

            new Scenario(
                "sort-single-list-once",
                "Sort a single in-memory list and immediately display results.",
                Recommendation.NOT_MEANINGFULLY_CONCURRENT,
                "There is only one computation task. Introducing concurrency would add overhead and synchronization complexity without improving performance or responsiveness."
            ),

            // Extension Challenge (real-world example)
            new Scenario(
                "web-browser-tabs",
                "Each browser tab runs independently to improve stability and security.",
                Recommendation.PROCESS,
                "Browsers isolate tabs into separate processes so that a crash, memory leak, or exploit in one tab does not affect others. This is a deliberate tradeoff of higher memory usage for stronger fault isolation."
            )
        );
    }

    record Scenario(String name,
                    String description,
                    Recommendation recommendation,
                    String reasoning) 
    {

    }

    enum Recommendation 
    {
        PROCESS,
        THREAD,
        NOT_MEANINGFULLY_CONCURRENT
    }
}