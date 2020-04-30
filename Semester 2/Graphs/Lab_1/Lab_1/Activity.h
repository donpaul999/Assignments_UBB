//
// Created by Paul Colta on 25/04/2020.
//

#ifndef LAB_1_ACTIVITY_H
#define LAB_1_ACTIVITY_H


class Activity {
    int nodeIndex;
    int duration;
    int earliestStartTime;
    int earliestEndTime;
    int latestStartTime;
    int latestEndTime;

public:
    Activity();
    Activity(int nodeIndex, int duration);
    bool isCritical() const;
    void setEarliestStartTime(int earliestStartTime);
    void setEarliestEndTime(int earliestEndTime);
    void setLatestStartTime(int latestStartTime);
    void setLatestEndTime(int latestEndTime);
    int getDuration() const;
    int getEarliestStartTime() const;
    int getEarliestEndTime() const;
    int getLatestStartTime() const;
    int getLatestEndTime() const;
    int getNodeIndex() const;
};



#endif //LAB_1_ACTIVITY_H
