// const apiServerUrl = process.env.REACT_APP_API_JSON_SERVER_URL;

export const getCurrentAddress = async () => {
    // const config = {
    //   url: `${apiServerUrl}/api-geo-address-current-location`,
    //   method: "GET",
    //   headers: {
    //     "content-type": "application/json",
    //   },
    // };
    // const { data, error } = await callExternalApi({ config });

    // mock the response
    const {data} = await new Promise(resolve => {
        setTimeout(() => resolve({data: "2 rue Saint George, 72818 Vienne"}), 500);
    })

    return {
        data: data || null,
        // error,
    };
};
