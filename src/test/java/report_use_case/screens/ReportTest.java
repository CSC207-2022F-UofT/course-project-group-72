package report_use_case.screens;

import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.ReviewFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    static Report report;

    static ReportFactory reportFactory = new ReportFactory();

    static Review review = new ReviewFactory().create("reviewID", 5, "reviewContent", "reviewerUsername", "Address");
    @BeforeAll
    static void setUp() {
        report = reportFactory.create("reason", review, "reporter_id");

    }

    @Test
    void getReason() {
        assertEquals("reason", report.getReason());
    }

    @Test
    void getReview() {
        assertEquals(review, report.getReview());
    }

    @Test
    void getReviewId() {
        assertEquals("reviewID", report.getReviewId());
    }


    @Test
    void getReporterUsername() {
        assertEquals("reporter_id", report.getReporterUsername());
    }

    @Test
    void getReportedUser() {
        assertEquals("reviewerUsername", report.getReportedUser());
    }

    @Test
    void getReviewContent() {
        assertEquals("reviewContent", report.getReviewContent());
    }
}