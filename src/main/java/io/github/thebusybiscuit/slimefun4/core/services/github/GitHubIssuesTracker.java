package io.github.thebusybiscuit.slimefun4.core.services.github;

import java.util.logging.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONElement;
import kong.unirest.json.JSONObject;
import me.mrCookieSlime.Slimefun.api.Slimefun;

class GitHubIssuesTracker extends GitHubConnector {

    @FunctionalInterface
    interface IssuesCallback {

        void update(int issues, int pullRequests);

    }

    private final IssuesCallback callback;

    GitHubIssuesTracker(GitHubService github, String repository, IssuesCallback callback) {
        super(github, repository);
        this.callback = callback;
    }

    @Override
    public void onSuccess(JsonNode element) {
        if (element.isArray()) {
            JSONArray array = element.getArray();

            int issues = 0;
            int pullRequests = 0;

            // Don't even question why this is an Iterable<Object>
            for (Object elem : array) {
                JSONObject obj = ((JsonNode) elem).getObject();

                if (obj.has("pull_request")) {
                    pullRequests++;
                }
                else {
                    issues++;
                }
            }

            callback.update(issues, pullRequests);
        }
        else {
            Slimefun.getLogger().log(Level.WARNING, "Received an unusual answer from GitHub, possibly a timeout? ({0})", element);
        }
    }

    @Override
    public String getFileName() {
        return "issues";
    }

    @Override
    public String getURLSuffix() {
        return "/issues?per_page=100";
    }

}
