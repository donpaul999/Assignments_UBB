//
// Created by Paul Colta on 25/04/2020.
//

#include "DAGUI.h"

DAGUI::DAGUI(const std::string &fileName): activityGraph{DIRECT_GRAPH()} {
    activityGraph.loadActivityGraphFromFile(fileName);
}

void DAGUI::runApplication() {
    std::cout<<"****************************\n";
    std::vector<Activity> activityStatus = activityGraph.getFullActivityStatus();
    int totalTime = -1;

    for (const Activity& activity: activityStatus) {
        std::cout << "Activity #" << activity.getNodeIndex() << "\n"
                  << "\tEarliest start: " << activity.getEarliestStartTime() << '\n'
                  << "\tEarliest end: " << activity.getEarliestEndTime() << '\n'
                  << "\tLatest start: " << activity.getLatestStartTime() << '\n'
                  << "\tLatest end: " << activity.getLatestEndTime() << '\n';
        totalTime = std::max(totalTime, activity.getEarliestEndTime());
    }
    std::cout << "Critical activities: ";
    for (const Activity& activity: activityStatus) {
        if (activity.isCritical()) {
            std::cout << activity.getNodeIndex() << " ";
        }
    }
    std::cout << "\nTOTAL TIME: " << totalTime << '\n';
    std::cout<<"****************************\n";
}
