import React from "react";
import {PageLayout} from "../components/page-layout";
import {useNavigate} from "react-router-dom";
import {useAuth0} from "@auth0/auth0-react";
import EnquireForm from "../components/enquire-form";
import {confirmBooking} from "../services/cap-booking.service";
import {PageLoader} from "../components/page-loader";

export const HomePage = () => {
    const queryParameters = new URLSearchParams(window.location.search);
    const appStateJSON = queryParameters.get("stateParams");

    const navigate = useNavigate();

    const previousState = !!appStateJSON ? JSON.parse(appStateJSON) : undefined;

    const {isAuthenticated, loginWithRedirect, getAccessTokenSilently} =
        useAuth0();

    const sendConfirmation = async (request) => {
        const accessToken = await getAccessTokenSilently();
        try {
            const {data} = await confirmBooking(accessToken, request);
            !!data && navigate("/booking-confirmation");
        } catch (e) {
            console.error("confirmation failed");
        }
    };

    const handleConfirmRequest = async (request) => {
        if (isAuthenticated) {
            await sendConfirmation(request);
        } else {
            void loginWithRedirect({
                appState: {
                    stateParams: encodeURIComponent(JSON.stringify(request)),
                    onRedirecting: () => (
                        <div className="page-layout">
                            <PageLoader />
                        </div>
                    ),
                },
            });
        }
    };

    return (
        <PageLayout>
            <h3 className="enquiry--title">Book a cab</h3>

            <EnquireForm
                prefilled={previousState}
                onConfirmed={handleConfirmRequest}
            />
        </PageLayout>
    );
};
